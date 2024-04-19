package ru.antonovmikhail.customer.service;



import ru.antonovmikhail.customer.model.Customer;
import ru.antonovmikhail.customer.model.CustomerDtoIn;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer findById(UUID id);

    List<Customer> findAll();

    Customer save(CustomerDtoIn userDtoIn);

    Customer update(CustomerDtoIn userDtoIn);

    Customer delete(UUID id);

}
