package com.bcb.core.rest;

import com.bcb.core.domain.MessageService;
import com.bcb.core.exception.BCBException;
import com.bcb.core.rest.model.SendMessageRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequestBody requestBody) {
        try {
            service.sendMessage(
                    requestBody.getMessageSender().getCustomerId(),
                    requestBody.getMessageRecipient().getRecipientPhone(),
                    requestBody.getIsWhatsapp(),
                    requestBody.getMessageText()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BCBException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
