package lk.ijse.multishop.service;

import lk.ijse.multishop.customObj.CustomerResponse;
import lk.ijse.multishop.customObj.ItemResponse;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.dto.impl.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String id,ItemDTO itemDTO);
    void deleteItem( String id );
    ItemResponse getSelectedItem(String id);
    List<ItemDTO> getAllItems();
    String genarateNewItemId();
}
