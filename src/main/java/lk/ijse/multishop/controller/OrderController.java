package lk.ijse.multishop.controller;

import lk.ijse.multishop.dto.impl.PlaceOrderDTO;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.exeption.NotPlacedOrderException;
import lk.ijse.multishop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    @Autowired
    private OrderService orderService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderDTO orderDto){
        if (orderDto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            orderService.placeOrder(orderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NotPlacedOrderException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
