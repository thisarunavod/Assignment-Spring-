package lk.ijse.multishop.dto.impl;

import lk.ijse.multishop.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO implements SuperDTO {

    private String orderId;
    private String description;
    private String customerId;
    private ArrayList<String> itemIdList;
    private ArrayList<String> quantityList;

}
