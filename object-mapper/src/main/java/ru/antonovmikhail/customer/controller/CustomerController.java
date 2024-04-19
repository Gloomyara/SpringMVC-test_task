package ru.antonovmikhail.customer.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.customer.model.CustomerDtoIn;
import ru.antonovmikhail.customer.service.CustomerService;
import ru.antonovmikhail.util.Views;

import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class CustomerController {

    private final CustomerService service;
    private final ObjectMapper objectMapper;

    @JsonView(Views.UserSummary.class)
    @GetMapping("customers")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Received GET api/v1/customers request.");
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.findAll()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @JsonView(Views.UserDetails.class)
    @GetMapping("customer/{id}")
    public ResponseEntity<String> getById(@org.hibernate.validator.constraints.UUID UUID id) {
        try {
            log.info("Received GET api/v1/customer/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.findById(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @JsonView(Views.UserDetails.class)
    @PostMapping("customer")
    public ResponseEntity<String> post(@RequestBody String json) {
        try {
            @Valid
            CustomerDtoIn dtoIn = objectMapper.readValue(json, CustomerDtoIn.class);
            log.info("Received POST api/v1/customer request, userDtoIn = {}", dtoIn);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(objectMapper.writeValueAsString(service.save(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @JsonView(Views.UserDetails.class)
    @PutMapping("customer")
    public ResponseEntity<String> put(@RequestBody String json) {
        try {
            @Valid
            CustomerDtoIn dtoIn = objectMapper.readValue(json, CustomerDtoIn.class);
            log.info("Received PUT api/v1/customer request, userDtoIn = {}", dtoIn);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.update(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @JsonView(Views.UserDetails.class)
    @DeleteMapping("customer/{id}")
    public ResponseEntity<String> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        try {
            log.info("Received DELETE api/v1/customer/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(service.delete(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
