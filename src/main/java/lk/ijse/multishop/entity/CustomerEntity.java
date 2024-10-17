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
@Table(name = "customer")
public class CustomerEntity {


    @Id
    private String id;

    private String name ;

    private String address;

    private double salary;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    private List<OrderEntity> orders = new ArrayList<>();

}
