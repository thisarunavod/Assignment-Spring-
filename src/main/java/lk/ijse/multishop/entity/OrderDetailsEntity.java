package lk.ijse.multishop.entity;

import jakarta.persistence.*;
import lk.ijse.multishop.entity.embedded.OrderDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "OrderDetails")

public class OrderDetailsEntity {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;

    @Column(name = "orderPrice")
    private double price;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId",
            insertable = false,
            updatable = false)
    private OrderEntity order;


    @ManyToOne
    @JoinColumn(name = "id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private ItemEntity item;

}
