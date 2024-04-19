package ru.antonovmikhail.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDtoOut {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
}
