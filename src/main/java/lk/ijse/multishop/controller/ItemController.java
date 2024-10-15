package lk.ijse.multishop.controller;


import lk.ijse.multishop.Util.AppUtil;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.dto.impl.ItemDTO;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.service.CustomerService;
import lk.ijse.multishop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
//@CrossOrigin
public class ItemController {


    @Autowired
    private final ItemService itemService;

    @GetMapping("health")
    public String healthChecker(){ return "ItemController Runnng Perfectly";}
    @PostMapping(consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveItem(
            @RequestPart("id") String id,
            @RequestPart("description") String description,
            @RequestPart("quantity") Double quantity,
            @RequestPart("unit") String unit,
            @RequestPart("price") Double price,
            @RequestPart("itemPic") String itemPic ){


        try {
            // Handle item picture
            byte[] imageByteCollection = itemPic.getBytes();
            String base64ItemPic = AppUtil.toBase64ProfilePic(imageByteCollection);  /*<--- converting to base64 format*/

            // build the item
            ItemDTO buildItemDTO = new ItemDTO();
            buildItemDTO.setId(id);
            buildItemDTO.setDescription(description);
            buildItemDTO.setQuantity(quantity);
            buildItemDTO.setUnit(unit);
            buildItemDTO.setPrice(price);
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


}
