package com.bcb.core.rest;

import com.bcb.core.domain.CustomerService;
import com.bcb.core.domain.model.Customer;
import com.bcb.core.exception.BCBException;
import com.bcb.core.rest.model.AddCreditRequestBody;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService service;

    @Test
    public void whenRequestGetCustomersShouldReturnCustomerJsonList() throws Exception {

        Gson gson = new Gson();

        Customer customer = new Customer();
        customer.setName("teste");
        List<Customer> customersList = Arrays.asList(customer);

        Mockito.when(service.getCustomers()).thenReturn(customersList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(customersList)));
    }

    @Test
    public void whenRequestGetCustomerByIdShouldReturnCustomerJsonObject() throws Exception {

        Gson gson = new Gson();

        Customer customer = new Customer();
        customer.setName("teste");

        Mockito.when(service.getCustomerById(1L)).thenReturn(customer);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(customer)));
    }

    @Test
    public void whenRequestGetCustomerByIdWithInvalidIdShouldReturnNotFound() throws Exception {

        Mockito.when(service.getCustomerById(2L)).thenThrow(new BCBException("Resource not found", HttpStatus.NOT_FOUND));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenRequestAddCreditToCustomerShouldReturnSuccess() throws Exception {
        Gson gson = new Gson();

        AddCreditRequestBody requestBody = new AddCreditRequestBody();
        requestBody.setAmount(BigDecimal.valueOf(12.50));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/customers/1/credit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestAddCreditToInvalidCustomerShouldReturnNotFound() throws Exception {
        Gson gson = new Gson();

        AddCreditRequestBody requestBody = new AddCreditRequestBody();
        requestBody.setAmount(BigDecimal.valueOf(12.50));

        Mockito.doThrow(new BCBException("Resource not found", HttpStatus.NOT_FOUND)).when(service).addCredit(2L, BigDecimal.valueOf(12.50));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/customers/2/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenRequestAddCreditToCustomerWithInvalidPayloadShouldReturnBadRequest() throws Exception {
        Gson gson = new Gson();

        AddCreditRequestBody requestBody = new AddCreditRequestBody();
        requestBody.setAmount(BigDecimal.valueOf(0));

        Mockito.doThrow(new BCBException("Invalid Payload", HttpStatus.BAD_REQUEST)).when(service).addCredit(2L, BigDecimal.valueOf(0));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/customers/2/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenRequestSwitchPlanShouldReturnSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/customers/1/plan"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(service, Mockito.times(1)).switchPlanType(1L);
    }

    @Test
    public void whenRequestSwitchPlanWithInvalidIdShouldReturnNotFound() throws Exception {
        Mockito.doThrow(new BCBException("Resource not found", HttpStatus.NOT_FOUND)).when(service).switchPlanType(1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/customers/1/plan"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
