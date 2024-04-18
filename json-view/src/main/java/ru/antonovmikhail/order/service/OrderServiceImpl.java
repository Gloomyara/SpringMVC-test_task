package ru.antonovmikhail.order.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.user.repository.UserRepository;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public Order getOrderById(UUID id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Order saveNewOrder(OrderDtoIn dtoIn) throws EntityNotFoundException {
        if (userRepository.existsById(dtoIn.getUserId())) {
            Order order = Order.builder().build();
            repository.save(order);
            return order;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Order update(OrderDtoIn dtoIn) throws EntityNotFoundException {
        if (userRepository.existsById(dtoIn.getUserId())) {
            Order order = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
            order.setPrice(dtoIn.getPrice());
            order.setDescription(dtoIn.getDescription());
            order.setProductName(dtoIn.getProductName());
            if (dtoIn.getStatus().equals(OrderDtoIn.Status.PAID)){
                order.setPaid(true);
            } else {
                order.setPaid(false);
            }
            return order;
        }
        throw new EntityNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order delete(UUID id) throws EntityNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return order;
    }
}
