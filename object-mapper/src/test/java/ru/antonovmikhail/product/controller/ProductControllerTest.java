package ru.antonovmikhail.product.controller;

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
import ru.antonovmikhail.product.model.dto.NewProductDto;
import ru.antonovmikhail.product.model.dto.ProductDtoOut;
import ru.antonovmikhail.product.service.ProductService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;
    private final NewProductDto dtoIn = new NewProductDto("Лампочка", "китайская", BigDecimal.valueOf(100));

    private final ProductDtoOut dtoOut = new ProductDtoOut("Лампочка", "китайская", BigDecimal.valueOf(100));

    @SneakyThrows
    @Test
    void postWhenDtoInCorrect() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(dtoIn);
        when(service.saveNewProduct(dtoIn)).thenReturn(dtoOut);
        System.out.println(mvc.perform(post("/api/v1/product")
                        .content(json)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(dtoOut.getName()))));
        verify(service, times(1))
                .saveNewProduct(any(NewProductDto.class));
    }

}