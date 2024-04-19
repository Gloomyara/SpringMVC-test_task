package ru.antonovmikhail.order.mapper;

import org.mapstruct.*;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.order.model.dto.NewOrderDto;
import ru.antonovmikhail.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.order.model.dto.OrderDtoOut;

import java.util.List;

@Mapper(uses = {OrderProductMapper.class})
public interface OrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "entity.updateDate", expression = ("java(LocalDateTime.now())"))
    Order toEntity(NewOrderDto dto);

    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "deliveryAddress", source = "customer.address")
    OrderDtoOut toDto(Order entity);

    List<OrderDtoOut> toDto(List<Order> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(OrderDtoIn dto, @MappingTarget Order entity);
}