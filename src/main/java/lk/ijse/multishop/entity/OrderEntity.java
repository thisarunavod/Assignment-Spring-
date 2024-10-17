package lk.ijse.multishop.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "`order`")
public class OrderEntity {

    @Id
    private String orderId;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "orderDateTime")
    private Timestamp orderDateTime;

    @ManyToOne
    @JoinColumn(name = "id")
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();


}
