package lk.ijse.multishop.dto.impl;

import lk.ijse.multishop.customObj.ItemResponse;
import lk.ijse.multishop.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements SuperDTO, ItemResponse {
    private String id;
    private String description;
    private double quantity;
    private String unit;
    private double price;
    private String itemPic;
}
