package lk.ijse.multishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
