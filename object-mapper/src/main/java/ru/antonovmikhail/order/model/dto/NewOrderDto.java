package ru.antonovmikhail.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewOrderDto {
    @NotNull
    @org.hibernate.validator.constraints.UUID
    @JsonProperty(value = "user_uuid")
    private UUID userId;
    private String description;
    private List<OrderProductDtoIn> productBatch;
}
