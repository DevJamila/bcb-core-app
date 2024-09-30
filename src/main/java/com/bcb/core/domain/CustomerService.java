package com.bcb.core.domain;

import com.bcb.core.domain.model.Customer;
import com.bcb.core.domain.model.CustomerPlanTypeEnum;
import com.bcb.core.exception.BCBException;
import com.bcb.core.persistence.model.CustomerEntity;
import com.bcb.core.persistence.model.CustomerPlanEntity;
import com.bcb.core.persistence.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final BigDecimal POSTPAID_CREDIT_BASE_AMOUNT = BigDecimal.valueOf(20.00);

    @Autowired
    CustomerRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public List<Customer> getCustomers() {
        List<CustomerEntity> customerEntities = repository.findAll();
        List<Customer> customers = customerEntities.stream().map(entity -> modelMapper.map(entity, Customer.class)).toList();
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Optional<CustomerEntity> customerEntity = repository.findById(id);
        if (customerEntity.isEmpty()) {
            throw new BCBException("Resource not found", HttpStatus.NOT_FOUND);
        } else {
            return modelMapper.map(customerEntity.get(), Customer.class);
        }
    }

    public void addCredit(Long customerId, BigDecimal amount) {
        Optional<CustomerEntity> customerEntityWrapper = repository.findById(customerId);

        if (customerEntityWrapper.isEmpty()) {
            throw new BCBException("Resource not found", HttpStatus.NOT_FOUND);
        } else {
            CustomerEntity customerEntity = customerEntityWrapper.get();

            checkCustomerPlanAllowsCreditAddition(customerEntity.getCustomerPlan());
            checkAmountValueIsValid(amount);

            BigDecimal currentCreditAmount = customerEntity.getCustomerPlan().getAmount();
            customerEntity.getCustomerPlan().setAmount(currentCreditAmount.add(amount));

            repository.save(customerEntity);
        }
    }

    public void switchPlanType(Long customerId) {
        Optional<CustomerEntity> customerEntityWrapper = repository.findById(customerId);

        if (customerEntityWrapper.isEmpty()) {
            throw new BCBException("Resource not found", HttpStatus.NOT_FOUND);
        } else {
            CustomerEntity customerEntity = customerEntityWrapper.get();
            CustomerPlanEntity plan = customerEntity.getCustomerPlan();

            if (plan.getPlanType() == CustomerPlanTypeEnum.PREPAID) {
                plan.setAmount(POSTPAID_CREDIT_BASE_AMOUNT.add(plan.getAmount()));
                plan.setPlanType(CustomerPlanTypeEnum.POSTPAID);
            } else {
                plan.setPlanType(CustomerPlanTypeEnum.PREPAID);
            }

            customerEntity.setCustomerPlan(plan);

            repository.save(customerEntity);
        }
    }

    private void checkCustomerPlanAllowsCreditAddition(CustomerPlanEntity customerPlan) {
        if (CustomerPlanTypeEnum.PREPAID != customerPlan.getPlanType()) {
            throw new BCBException("Customer plan type does not allow credit addition.", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkAmountValueIsValid(BigDecimal value) {
        if (value.signum() < 1) {
            throw new BCBException("Amount to credit is invalid", HttpStatus.BAD_REQUEST);
        }
    }

}
