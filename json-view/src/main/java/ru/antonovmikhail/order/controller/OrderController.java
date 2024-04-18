package ru.antonovmikhail.order.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.service.OrderService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAll() {
        log.info("Received GET api/v1/users request.");
        return ResponseEntity.ok(orderService.findAll());
    }


    @GetMapping("orders/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable UUID id) {
        log.info("Received GET api/v1/orders/{} request.", id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("order")
    public ResponseEntity<Order> createNewOrder(
            @Valid @RequestBody OrderDtoIn dtoIn) {
        log.info("Received POST api/v1/order request, orderDtoIn: {}.", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.saveNewOrder(dtoIn));
    }

    @PutMapping("order")
    public ResponseEntity<Order> put(@Valid @RequestBody OrderDtoIn dtoIn) {
        log.info("Received PUT api/v1/order request, orderDtoIn = {}", dtoIn);
        return ResponseEntity.ok(orderService.update(dtoIn));
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<Order> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received DELETE api/v1/order/{} request.", id);
        return ResponseEntity.ok(orderService.delete(id));
    }
}



