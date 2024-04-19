package ru.antonovmikhail.order.model.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDtoIn {
    @org.hibernate.validator.constraints.UUID
    UUID orderId;
    @org.hibernate.validator.constraints.UUID
    UUID productId;
    @Positive
    Integer quantity;
}
