package lk.ijse.multishop.service;


import lk.ijse.multishop.customObj.CustomerResponse;
import lk.ijse.multishop.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String noteId,CustomerDTO customerDTO);
    void deleteCustomer( String id );
    CustomerResponse getSelectedCustomer(String id);
    List<CustomerDTO> getAllCustomer();

    String genarateNewCustomerId();
}
