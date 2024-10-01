package com.bcb.core.domain.model;

public enum MessageStatusEnum {

    SENDING("SENDING"),
    SUCCESS("SUCCESS"),
    CANCELED("CANCELED");

    private final String messageStatus;

    MessageStatusEnum(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageStatus() {
        return messageStatus;
    }
}
