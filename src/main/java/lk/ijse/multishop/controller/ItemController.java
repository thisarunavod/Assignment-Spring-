package lk.ijse.multishop.controller;


import lk.ijse.multishop.Util.AppUtil;
import lk.ijse.multishop.customObj.ItemResponse;
import lk.ijse.multishop.dto.impl.ItemDTO;
import lk.ijse.multishop.exeption.CustomerNotFoundException;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.exeption.ItemNotFoundException;
import lk.ijse.multishop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
//@CrossOrigin
public class ItemController {


    @Autowired
    private final ItemService itemService;

    @GetMapping("health")
    public String healthChecker(){ return "ItemController Runnng Perfectly";}

    @PostMapping (consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveItem(
            @RequestPart("id") String id,
            @RequestPart("description") String description,
            @RequestPart("quantity") String quantity,
            @RequestPart("unit") String unit,
            @RequestPart("price") String price,
            @RequestPart("itemPic") MultipartFile itemPic ){


        try {
            // Handle item picture
            byte[] imageByteCollection = itemPic.getBytes();
            String base64ItemPic = AppUtil.toBase64ProfilePic(imageByteCollection);  /*<--- converting to base64 format*/

            // build the item
            ItemDTO buildItemDTO = new ItemDTO();
            buildItemDTO.setId(id);
            buildItemDTO.setDescription(description);
            buildItemDTO.setQuantity(Double.parseDouble(quantity));
            buildItemDTO.setUnit(unit);
            buildItemDTO.setPrice(Double.parseDouble(price));
            buildItemDTO.setItemPic(base64ItemPic);

            //Send to service layer
            itemService.saveItem(buildItemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "newId",produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateNewItemId(){
        return itemService.genarateNewItemId();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getSelectedItem(@PathVariable("id") String itemId){
        return itemService.getSelectedItem(itemId);
    }

    @GetMapping(value = "getAllItems",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){ return itemService.getAllItems(); }

    @PatchMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateItem(@PathVariable("id") String id ,
                                             @RequestPart("description") String description,
                                             @RequestPart("quantity") String quantity,
                                             @RequestPart("unit") String unit,
                                             @RequestPart("price") String price,
                                             @RequestPart("itemPic") MultipartFile itemPic ) {


        try {
            byte[] imageByteCollection = itemPic.getBytes();
            String updateBase64ItemPic = AppUtil.toBase64ProfilePic(imageByteCollection);
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(id);
            itemDTO.setDescription(description);
            itemDTO.setQuantity(Double.parseDouble(quantity));
            itemDTO.setUnit(unit);
            itemDTO.setPrice(Double.parseDouble(price));
            itemDTO.setItemPic(updateBase64ItemPic);

            itemService.updateItem(id, itemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id){
        try {
            if ( id == null || id.isEmpty()) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
