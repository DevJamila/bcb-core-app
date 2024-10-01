package com.bcb.core.rest.model;

public class SMSRequestBody {

    private String senderPhoneNumber;
    private String recipientPhoneNumber;
    private String messageText;

    public SMSRequestBody(String senderPhoneNumber, String recipientPhoneNumber, String messageText) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.messageText = messageText;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public String getMessageText() {
        return messageText;
    }
}
