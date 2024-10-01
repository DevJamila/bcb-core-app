package com.bcb.core.domain;

import com.bcb.core.domain.model.*;
import com.bcb.core.exception.BCBException;
import com.bcb.core.persistence.model.CustomerEntity;
import com.bcb.core.persistence.model.MessageEntity;
import com.bcb.core.persistence.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class MessageService {

    private final BigDecimal MESSAGE_COST = BigDecimal.valueOf(0.25);

    @Autowired
    private MessageRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    public void sendMessage(Long customerId, String recipientPhone, Boolean isWhatsapp, String messageText) {

        validateSendMessageInfo(recipientPhone, messageText);
        Customer customer = customerService.getCustomerById(customerId);

        payForMessage(customer);

        saveMessage(buildMessage(customer, recipientPhone, isWhatsapp, messageText));

        // todo call sms simulator
    }

    private MessageEntity buildMessage(Customer customer, String recipientPhone, Boolean isWhatsapp, String messageText) {
        MessageEntity entity = new MessageEntity();
        entity.setCustomer(modelMapper.map(customer, CustomerEntity.class));
        entity.setRecipientPhone(recipientPhone);
        entity.setWhatsapp(isWhatsapp != null ? isWhatsapp : false);
        entity.setMessageText(messageText);
        entity.setMessageStatus(MessageStatusEnum.SENDING);
        entity.setRequestTimestamp(new Date());

        return entity;
    }

    private void saveMessage(MessageEntity entity) {
        repository.save(entity);
    }

    private void payForMessage(Customer customer) {
        checkPlanCredit(customer.getCustomerPlan());

        customerService.chargeCustomer(customer.getId(), MESSAGE_COST);
    }

    private void checkPlanCredit(CustomerPlan customerPlan) {
        BigDecimal currentAmount = customerPlan.getAmount();
        if (currentAmount.compareTo(MESSAGE_COST) < 0) {
            String errorMessage = "";
            if (CustomerPlanTypeEnum.PREPAID == customerPlan.getPlanType()) {
                errorMessage = "You don't have enough balance. Please add more credit to your plan and try again.";
            } else {
                errorMessage = "Your monthly credit has been run out. Wait for the next invoice or switch your plan to prepaid to add credits.";
            }
            throw new BCBException(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateSendMessageInfo(String recipientPhone, String messageText) {
        validatePhoneNumber(recipientPhone);
        validateMessageText(messageText);
    }

    private void validatePhoneNumber(String phoneNumber) {
        String errorMessage = "";

        if (phoneNumber == null) {
            errorMessage = "Recipient phone number is missing.";
        } else if (phoneNumber.length() != 11) {
            errorMessage = "The phone number must be the DDD + number.";
        } else {
            try {
                Long convertedPhone = Long.parseLong(phoneNumber);
            } catch (NumberFormatException e) {
                errorMessage = "The phone number must be just digits.";
            }
        }

        if (!errorMessage.isBlank()) {
            throw new BCBException(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateMessageText(String text){
        if (text == null || text.isBlank()) {
            throw new BCBException("The message content is missing.", HttpStatus.BAD_REQUEST);
        }
    }

}
