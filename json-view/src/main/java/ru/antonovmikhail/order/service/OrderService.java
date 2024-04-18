package ru.antonovmikhail.order.service;

import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;

import java.util.List;
import java.util.UUID;

public interface OrderService {


    Order getOrderById(UUID id);

    Order saveNewOrder(OrderDtoIn dto);

    Order update(OrderDtoIn dtoIn);

    @Transactional(readOnly = true)
    List<Order> findAll();

    Order delete(UUID id);
}
