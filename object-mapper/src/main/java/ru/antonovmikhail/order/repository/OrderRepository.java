package ru.antonovmikhail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
