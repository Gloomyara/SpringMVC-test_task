package ru.antonovmikhail.order.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import ru.antonovmikhail.product.model.Product;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "order_products")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Order order;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;
    private Integer quantity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrderProduct orderProduct = (OrderProduct) o;
        return getId() != null && Objects.equals(getId(), orderProduct.getId());
    }
}

