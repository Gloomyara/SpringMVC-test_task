package ru.antonovmikhail.customer.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.customer.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @EntityGraph(attributePaths = {"orderList"})
    Optional<Customer> findById(UUID id);
}
