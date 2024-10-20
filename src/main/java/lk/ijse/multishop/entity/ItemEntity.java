package lk.ijse.multishop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String id;
    private String description;
    private double quantity;
    private String unit;
    private double price;
    @Column(columnDefinition = "LONGTEXT")
    private String itemPic;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "item")
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();

}
