package ru.antonovmikhail.order.service;

import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.NewOrderDto;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.model.dto.OrderDtoOut;

import java.util.List;
import java.util.UUID;

public interface OrderService {


    OrderDtoOut getOrderById(UUID id);

    OrderDtoOut saveNewOrder(NewOrderDto dto);

    OrderDtoOut update(OrderDtoIn dtoIn);

    List<OrderDtoOut> findAll();

    OrderDtoOut delete(UUID id);
}
