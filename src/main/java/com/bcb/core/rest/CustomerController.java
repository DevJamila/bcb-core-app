package com.bcb.core.rest;

import com.bcb.core.domain.CustomerService;
import com.bcb.core.domain.model.Customer;
import com.bcb.core.domain.model.Message;
import com.bcb.core.exception.BCBException;
import com.bcb.core.rest.model.AddCreditRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List> getCustomers() {
        try {

            List customers = service.getCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);

        } catch (BCBException e) {
            return new ResponseEntity<>(new ArrayList(Arrays.asList(e.getMessage())), e.getStatus());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = service.getCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);

        } catch (BCBException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @PostMapping("/{id}/credit")
    public ResponseEntity<String> addCredit(@PathVariable Long id, @RequestBody AddCreditRequestBody creditRequest) {
        try {
            service.addCredit(id, creditRequest.getAmount());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BCBException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @PutMapping("/{id}/plan")
    public ResponseEntity<String> switchPlanType(@PathVariable Long id) {
        try {
            service.switchPlanType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BCBException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

}
