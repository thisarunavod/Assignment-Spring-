package lk.ijse.multishop.service;

import lk.ijse.multishop.Util.Mapping;
import lk.ijse.multishop.customObj.CustomerErrorResponse;
import lk.ijse.multishop.customObj.ItemErrorResponse;
import lk.ijse.multishop.customObj.ItemResponse;
import lk.ijse.multishop.dao.ItemDAO;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.dto.impl.ItemDTO;
import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.ItemEntity;
import lk.ijse.multishop.exeption.CustomerNotFoundException;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.exeption.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<ItemEntity> tmpItemEntitybyId = itemDAO.findById(id);
        if (!tmpItemEntitybyId.isPresent()) throw  new ItemNotFoundException("Item" +
                " Not Found");

        tmpItemEntitybyId.get().setDescription(itemDTO.getDescription());
        tmpItemEntitybyId.get().setQuantity(itemDTO.getQuantity());
        tmpItemEntitybyId.get().setUnit(itemDTO.getUnit());
        tmpItemEntitybyId.get().setPrice(itemDTO.getPrice());
        tmpItemEntitybyId.get().setItemPic(itemDTO.getItemPic());
    }

    @Override
    public void deleteItem(String id) {
        Optional<ItemEntity> tmpItemEntitybyId = itemDAO.findById(id);
        if (!tmpItemEntitybyId.isPresent()) throw  new ItemNotFoundException("Item Not Found");
        itemDAO.deleteById(id);
    }

    @Override
    public ItemResponse getSelectedItem(String id) {

        if (itemDAO.existsById(id)) {
            return mapping.convertToItemDTO(itemDAO.getItemEntityById(id));
        }
        return new ItemErrorResponse(0,"Item Not Found");
    }

    @Override
    public List<ItemDTO> getAllItems() {

        return mapping.convertToItemDTOList(itemDAO.findAll());
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
