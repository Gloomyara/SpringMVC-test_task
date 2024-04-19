package ru.antonovmikhail.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.customer.model.CustomerDtoIn;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.NewOrderDto;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.service.OrderService;

import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public OrderController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("orders")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Received GET api/v1/users request.");
            return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.findAll()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }


    @GetMapping("orders/{id}")
    public ResponseEntity<String> getOrder(
            @PathVariable UUID id) {
        try {
            log.info("Received GET api/v1/orders/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.getOrderById(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("order")
    public ResponseEntity<String> createNewOrder(
            @RequestBody String json) {
        try {
            @Valid
            NewOrderDto dtoIn = objectMapper.readValue(json, NewOrderDto.class);
            log.info("Received POST api/v1/order request, orderDtoIn: {}.", dtoIn);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(objectMapper.writeValueAsString(orderService.saveNewOrder(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @PutMapping("order")
    public ResponseEntity<String> put(@RequestBody String json) {
        try {
            @Valid
            OrderDtoIn dtoIn = objectMapper.readValue(json, OrderDtoIn.class);
            log.info("Received PUT api/v1/order request, orderDtoIn = {}", dtoIn);
            return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.update(dtoIn)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<String> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        try {
            log.info("Received DELETE api/v1/order/{} request.", id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.delete(id)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}



