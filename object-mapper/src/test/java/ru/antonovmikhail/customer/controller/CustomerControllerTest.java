package ru.antonovmikhail.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonovmikhail.customer.model.Customer;
import ru.antonovmikhail.customer.model.CustomerDtoIn;
import ru.antonovmikhail.customer.service.CustomerService;
import ru.antonovmikhail.order.model.dto.NewOrderDto;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @MockBean
    private CustomerService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;
    private final CustomerDtoIn customerDtoIn = new CustomerDtoIn(null, "Buba", "whtvr@mail.org", "null");

    private final Customer customer = new Customer(null, "Buba", "whtvr@mail.org", "null");

    @SneakyThrows
    @Test
    void postWhenDtoInCorrect() {
        UUID uuid = UUID.randomUUID();
        customer.setId(uuid);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(customer);
        when(service.save(customerDtoIn)).thenReturn(customer);
        System.out.println(mvc.perform(post("/api/v1/customer")
                        .content(json)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", is(uuid.toString()))));
        verify(service, times(1))
                .save(any(CustomerDtoIn.class));
    }

}