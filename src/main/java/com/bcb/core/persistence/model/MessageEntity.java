package com.bcb.core.persistence.model;

import com.bcb.core.domain.model.MessageStatusEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bcb_message")
public class MessageEntity {

    @Id
    @SequenceGenerator(name = "bcb_message_message_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bcb_message_message_id_seq")
    @Column(name = "message_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerEntity customer;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "is_whatsapp")
    private Boolean isWhatsapp;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "message_status")
    @Enumerated(EnumType.STRING)
    private MessageStatusEnum messageStatus;

    @Column(name = "request_timestamp")
    private Date requestTimestamp;

    public MessageEntity() {
    }

    public MessageEntity(Long id, CustomerEntity customer, String recipientPhone, Boolean isWhatsapp, String messageText, MessageStatusEnum messageStatus, Date requestTimestamp) {
        this.id = id;
        this.customer = customer;
        this.recipientPhone = recipientPhone;
        this.isWhatsapp = isWhatsapp;
        this.messageText = messageText;
        this.messageStatus = messageStatus;
        this.requestTimestamp = requestTimestamp;
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
