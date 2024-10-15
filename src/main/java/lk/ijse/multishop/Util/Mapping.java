package lk.ijse.multishop.Util;

import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.dto.impl.ItemDTO;
import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertToCustomerEntity(CustomerDTO dto){
        return modelMapper.map(dto,CustomerEntity.class);
    }

    public List<CustomerDTO> convertToDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities,new TypeToken<List<CustomerDTO>>() {}.getType());

    }


    public ItemDTO convertToItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDTO.class);
    }

    public ItemEntity convertToItemEntity(ItemDTO dto){
        return modelMapper.map(dto,ItemEntity.class);
    }

    public List<ItemDTO> convertToItemDTOList(List<CustomerEntity> itemEntities){
        return modelMapper.map(itemEntities,new TypeToken<List<ItemDTO>>() {}.getType());
    }





}
