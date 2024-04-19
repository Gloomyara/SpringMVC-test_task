package ru.antonovmikhail.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.product.model.dto.NewProductDto;
import ru.antonovmikhail.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.product.service.ProductService;

import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
public class ProductController {
    private final ProductService service;
    private final ObjectMapper objectMapper;

    public ProductController(ProductService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("products")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Received GET api/v1/products request.");
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.findAll()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }


    @GetMapping("products/{id}")
    public ResponseEntity<String> getOrder(
            @PathVariable UUID id) {
        try {
            log.info("Received GET api/v1/products/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.getProductById(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("product")
    public ResponseEntity<String> saveNewProduct(
            @RequestBody String json) {
        try {
            @Valid
            NewProductDto dtoIn = objectMapper.readValue(json, NewProductDto.class);
            log.info("Received POST api/v1/product request, orderDtoIn: {}.", dtoIn);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(objectMapper.writeValueAsString(service.saveNewProduct(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @PutMapping("product")
    public ResponseEntity<String> put(@RequestBody String json) {
        try {
            @Valid
            ProductDtoIn dtoIn = objectMapper.readValue(json, ProductDtoIn.class);
            log.info("Received PUT api/v1/product request, orderDtoIn = {}", dtoIn);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.update(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        try {
            log.info("Received DELETE api/v1/product/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.delete(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}



