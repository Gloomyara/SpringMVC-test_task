package ru.antonovmikhail.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderProductId {
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "product_id")
    private UUID productId;
}
