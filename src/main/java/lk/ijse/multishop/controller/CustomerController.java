package lk.ijse.multishop.controller;

import jakarta.servlet.http.HttpServlet;
import lk.ijse.multishop.customObj.CustomerResponse;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.exeption.CustomerNotFoundException;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){

        if (customerDTO == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        try {
            customerService.saveCustomer(customerDTO);
            logger.info("Customer saved successfully: {}", customerDTO.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "newId",produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateNewCustomerId(){
        return customerService.genarateNewCustomerId();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectedCustomer(@PathVariable("id") String customerId){
        return customerService.getSelectedCustomer(customerId);
    }
    @GetMapping(value = "getAllCustomers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers(){ return customerService.getAllCustomer(); }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") String id , @RequestBody CustomerDTO dto){
        try {
            if ( dto == null && (id == null || id.isEmpty() )) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            customerService.updateCustomer(id , dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        try {
            if ( id == null || id.isEmpty()) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
