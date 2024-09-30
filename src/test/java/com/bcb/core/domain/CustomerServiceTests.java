package com.bcb.core.domain;

import com.bcb.core.domain.model.Customer;
import com.bcb.core.domain.model.CustomerPlanTypeEnum;
import com.bcb.core.exception.BCBException;
import com.bcb.core.persistence.model.CustomerEntity;
import com.bcb.core.persistence.model.CustomerPlanEntity;
import com.bcb.core.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.AssertionErrors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService service;

    @MockitoBean
    private CustomerRepository repository;

    @Test
    public void whenGetCustomersShouldReturnCustomerList() {
        CustomerEntity entity = new CustomerEntity(1L,
                                                    "Joana",
                                                    "joana@mail.com",
                                                    "44999888777",
                                                    "12312312312",
                                                    false,
                                                    null,
                                                    null,
                                                     new CustomerPlanEntity(
                                                             1L,
                                                             CustomerPlanTypeEnum.POSTPAID,
                                                             BigDecimal.valueOf(40.50)
                                                     ));

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(entity));

        List<Customer> customers = service.getCustomers();
        Assertions.assertTrue(customers.toArray().length > 0);
        Assertions.assertTrue(customers.get(0) instanceof Customer);
    }

    @Test
    public void whenGetCustomersShouldReturnEmptyList() {
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        List<Customer> customers = service.getCustomers();
        Assertions.assertTrue(customers.toArray().length == 0);
    }

    @Test
    public void whenGetCustmerByIdshouldReturnCustomerObject() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                                                        "Joana",
                                                        "joana@mail.com",
                                                        "44999888777",
                                                        "12312312312",
                                                        false,
                                                        null,
                                                        null,
                                                        new CustomerPlanEntity(
                                                                1L,
                                                                CustomerPlanTypeEnum.POSTPAID,
                                                                BigDecimal.valueOf(40.50)
                                                        ));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));

        Customer customer = service.getCustomerById(1L);
        Assertions.assertTrue(customer instanceof Customer);
        Assertions.assertTrue(customer.getName() == "Joana");
    }

    @Test
    public void whenGetCustomerByInvalidIdShouldThrowBCBException() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(BCBException.class, () -> service.getCustomerById(1L));
    }

    @Test
    public void whenAddCreditShouldCallSaveMethod() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                "Joana",
                "joana@mail.com",
                "44999888777",
                "12312312312",
                false,
                null,
                null,
                new CustomerPlanEntity(
                        1L,
                        CustomerPlanTypeEnum.PREPAID,
                        BigDecimal.valueOf(40.50)
                ));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));

        service.addCredit(1L, BigDecimal.valueOf(12.50));

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void whenAddCreditWithInvalidIdShouldThrowException() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(BCBException.class, () -> service.addCredit(1L, BigDecimal.valueOf(12.50)));
        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void whenAddCreditForPostpaidPlanShouldThrowException() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                "Joana",
                "joana@mail.com",
                "44999888777",
                "12312312312",
                false,
                null,
                null,
                new CustomerPlanEntity(
                        1L,
                        CustomerPlanTypeEnum.POSTPAID,
                        BigDecimal.valueOf(40.50)
                ));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));

        Assertions.assertThrows(BCBException.class, () -> service.addCredit(1L, BigDecimal.valueOf(12.50)));

        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void whenAddCreditWithInvalidAmountShouldThrowException() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                "Joana",
                "joana@mail.com",
                "44999888777",
                "12312312312",
                false,
                null,
                null,
                new CustomerPlanEntity(
                        1L,
                        CustomerPlanTypeEnum.PREPAID,
                        BigDecimal.valueOf(40.50)
                ));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));

        Assertions.assertThrows(BCBException.class, () -> service.addCredit(1L, BigDecimal.valueOf(0)));

        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void whenSwitchPlanFromPostpaidToPrepaidShouldSuccess() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                "Joana",
                "joana@mail.com",
                "44999888777",
                "12312312312",
                false,
                null,
                null,
                new CustomerPlanEntity(
                        1L,
                        CustomerPlanTypeEnum.POSTPAID,
                        BigDecimal.valueOf(40.50)
                ));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));


        service.switchPlanType(1L);

        Mockito.verify(repository, Mockito.times(1)).save(customerEntity);
    }

    @Test
    public void whenSwitchPlanFromPrepaidToPostpaidShouldSuccess() {
        CustomerEntity customerEntity = new CustomerEntity(1L,
                "Joana",
                "joana@mail.com",
                "44999888777",
                "12312312312",
                false,
                null,
                null,
                new CustomerPlanEntity(
                        1L,
                        CustomerPlanTypeEnum.PREPAID,
                        BigDecimal.valueOf(10.00)
                ));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));


        service.switchPlanType(1L);

        Mockito.verify(repository, Mockito.times(1)).save(customerEntity);
    }

    @Test
    public void whenSwitchPlanOfInvalidCustomerShouldThrowException() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(BCBException.class, () -> service.switchPlanType(1L));
    }
}
