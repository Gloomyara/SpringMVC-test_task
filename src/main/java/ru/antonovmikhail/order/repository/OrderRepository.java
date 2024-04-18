package ru.antonovmikhail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.order.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
