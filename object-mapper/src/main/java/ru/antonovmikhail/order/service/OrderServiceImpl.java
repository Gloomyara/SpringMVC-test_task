package ru.antonovmikhail.order.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.customer.model.Customer;
import ru.antonovmikhail.customer.repository.CustomerRepository;
import ru.antonovmikhail.order.mapper.OrderMapper;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.NewOrderDto;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.model.dto.OrderDtoOut;
import ru.antonovmikhail.order.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository repository;
    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Transactional(readOnly = true)
    @Override
    public OrderDtoOut getOrderById(UUID id) throws EntityNotFoundException {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public OrderDtoOut saveNewOrder(NewOrderDto dtoIn) throws EntityNotFoundException {
        Customer customer = customerRepository.findById(dtoIn.getUserId()).orElseThrow(() -> new EntityNotFoundException());
        Order order = mapper.toEntity(dtoIn);
        order.setCustomer(customer);
        order.setUpdateDate(LocalDateTime.now());
        return mapper.toDto(repository.save(order));
    }

    @Override
    public OrderDtoOut update(OrderDtoIn dtoIn) throws EntityNotFoundException {
        if (customerRepository.existsById(dtoIn.getCustomerId())) {
            Order order = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
            mapper.update(dtoIn, order);
            order.setPaid(dtoIn.getStatus().equals(OrderDtoIn.Status.PAID));
            order.setUpdateDate(LocalDateTime.now());
            return mapper.toDto(repository.save(order));
        }
        throw new EntityNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public OrderDtoOut delete(UUID id) throws EntityNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(order);
    }
}
