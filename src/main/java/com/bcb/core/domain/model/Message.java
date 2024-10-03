package com.bcb.core.domain.model;

import com.bcb.core.persistence.model.CustomerEntity;

import java.util.Date;

public class Message {

    private Long id;
    private CustomerEntity customer;
    private String recipientPhone;
    private Boolean isWhatsapp;
    private String messageText;
    private MessageStatusEnum messageStatus;
    private Date requestTimestamp;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public Boolean getWhatsapp() {
        return isWhatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        isWhatsapp = whatsapp;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageStatusEnum getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatusEnum messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }
}
