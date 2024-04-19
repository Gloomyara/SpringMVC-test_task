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
public class OrderDtoIn {
    @org.hibernate.validator.constraints.UUID
    private UUID id;
    @NotNull
    @org.hibernate.validator.constraints.UUID
    @JsonProperty(value = "customer_uuid")
    private UUID customerId;
    @NotNull
    Status status;
    private String description;
    private List<OrderProductDtoIn> productBatch;

    public enum Status {
        WISHLIST,
        PAID,
        PENDING,
    }
}
