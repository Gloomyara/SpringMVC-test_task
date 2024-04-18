package ru.antonovmikhail.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static ru.antonovmikhail.util.Constants.DATE_TIME_PATTERN;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoIn {
    @org.hibernate.validator.constraints.UUID
    private UUID id;
    @NotNull
    @org.hibernate.validator.constraints.UUID
    @JsonProperty(value = "user_uuid")
    private UUID userId;
    @NotNull
    Status status;
    @NotNull
    @Positive
    private BigDecimal price;
    private String description;
    private String productName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    public enum Status {
        WISHLIST,
        PAID,
        PENDING,
    }
}
