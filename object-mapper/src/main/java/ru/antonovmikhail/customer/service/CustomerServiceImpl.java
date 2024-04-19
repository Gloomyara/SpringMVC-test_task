package ru.antonovmikhail.customer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

import ru.antonovmikhail.customer.model.Customer;
import ru.antonovmikhail.customer.model.CustomerDtoIn;
import ru.antonovmikhail.customer.repository.CustomerRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Customer findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Customer save(CustomerDtoIn dtoIn) {
        Customer user = Customer.builder()
                .name(dtoIn.getName())
                .email(dtoIn.getEmail())
                .build();
        return repository.save(user);
    }

    @Override
    public Customer update(CustomerDtoIn dtoIn) {
        Customer user = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        user.setName(dtoIn.getName());
        user.setEmail(dtoIn.getEmail());
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer delete(UUID id) {
        Customer user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return user;
    }
}
