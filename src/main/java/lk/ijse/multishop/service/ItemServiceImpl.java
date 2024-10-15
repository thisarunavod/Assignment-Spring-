package lk.ijse.multishop.service;

import lk.ijse.multishop.Util.Mapping;
import lk.ijse.multishop.customObj.ItemResponse;
import lk.ijse.multishop.dao.ItemDAO;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.dto.impl.ItemDTO;
import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.ItemEntity;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private Mapping mapping;

    @Autowired
    ItemDAO itemDAO;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        if (itemDAO.existsById(itemDTO.getId()))  throw new DataPersistFailedException("Cannot Item Saved ");
        ItemEntity savedItem = itemDAO.save(mapping.convertToItemEntity(itemDTO));
        if (savedItem == null || savedItem.getId() == null) throw  new DataPersistFailedException("Cannot Item Saved ");
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public ItemResponse getSelectedItem(String id) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }

    @Override
    public String genarateNewItemId() {

        try {
            List<ItemDTO> allItemList = getAllItems();
            String id = allItemList.get(allItemList.size() - 1).getId();
            char[] charArray = id.toCharArray();
            String newID = "";
            for (int i = 1; i <= charArray.length-1 ; i++) { newID = newID + (charArray[i]); }
            int x = Integer.parseInt(newID);
            return "I"+(x+1);
        } catch (Exception e) {
            return "I1";
        }
    }
}
