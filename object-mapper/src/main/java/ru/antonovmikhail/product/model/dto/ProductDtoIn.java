package ru.antonovmikhail.product.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDtoIn {
    @org.hibernate.validator.constraints.UUID
    private UUID id;
    @org.hibernate.validator.constraints.UUID
    private UUID orderId;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotBlank
    private String name;
    private String description;

}
