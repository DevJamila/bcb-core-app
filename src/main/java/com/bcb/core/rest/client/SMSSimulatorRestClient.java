package com.bcb.core.rest.client;

import com.bcb.core.rest.model.SMSRequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SMSSimulatorRestClient {

    @Value("${bcb.sms-simulator.uri}")
    private String baseUri;

    RestClient restClient = RestClient.create();

    public HttpStatusCode sendSMS(String senderPhoneNumber, String recipientPhoneNumber, String messageText){
        SMSRequestBody smsRequestBody = new SMSRequestBody(senderPhoneNumber, recipientPhoneNumber, messageText);

        ResponseEntity<Void> response = restClient.post()
                .uri(baseUri + "/sms")
                .contentType(MediaType.APPLICATION_JSON)
                .body(smsRequestBody)
                .retrieve()
                .toBodilessEntity();

        return response.getStatusCode();
    }
}
