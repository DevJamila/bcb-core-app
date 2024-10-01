package com.bcb.core.rest.model;

import com.bcb.core.domain.model.Customer;
import com.bcb.core.domain.model.MessageStatusEnum;

import java.util.Date;

public class SendMessageRequestBody {

    private MessageSender messageSender;
    private MessageRecipient messageRecipient;
    private Boolean isWhatsapp;
    private String messageText;

    public SendMessageRequestBody() {
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public MessageRecipient getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(MessageRecipient messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public Boolean getIsWhatsapp() {
        return isWhatsapp;
    }

    public void setIsWhatsapp(Boolean whatsapp) {
        isWhatsapp = whatsapp;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
