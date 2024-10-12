package lk.ijse.multishop.service;

import lk.ijse.multishop.Util.Mapping;
import lk.ijse.multishop.customObj.CustomerErrorResponse;
import lk.ijse.multishop.customObj.CustomerResponse;
import lk.ijse.multishop.dao.CustomerDAO;
import lk.ijse.multishop.dto.impl.CustomerDTO;
import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.exeption.CustomerNotFoundException;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (customerDAO.existsById(customerDTO.getId()))  throw new DataPersistFailedException("Cannot Customer Saved ");
        CustomerEntity savedCustomer = customerDAO.save(mapping.convertToCustomerEntity(customerDTO));
        if (savedCustomer == null || savedCustomer.getId() == null) throw  new DataPersistFailedException("Cannot Customer Saved ");
    }

    @Override
    public void updateCustomer(String id, CustomerDTO dto) {

        Optional<CustomerEntity> tmpCustomerEntitybyId = customerDAO.findById(id);
        if (!tmpCustomerEntitybyId.isPresent()) throw  new CustomerNotFoundException("Customer Not Found");

        tmpCustomerEntitybyId.get().setName(dto.getName());
        tmpCustomerEntitybyId.get().setAddress(dto.getAddress());
        tmpCustomerEntitybyId.get().setSalary(dto.getSalary());

    }

    @Override
    public void deleteCustomer(String id) {
        Optional<CustomerEntity> tmpCustomerEntitybyId = customerDAO.findById(id);
        if (!tmpCustomerEntitybyId.isPresent()) throw  new CustomerNotFoundException("Customer Not Found");
        customerDAO.deleteById(id);
    }

    @Override
    public CustomerResponse getSelectedCustomer(String id) {
        if (customerDAO.existsById(id)) {
            return mapping.convertToCustomerDTO(customerDAO.getCustomerEntityById(id));
        }
        return new CustomerErrorResponse(0,"Customer Not Found");
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return mapping.convertToDTOList(customerDAO.findAll());
    }

    @Override
    public String genarateNewCustomerId() {

        try {
            List<CustomerDTO> allCustomerList = getAllCustomer();
            String id = allCustomerList.get(allCustomerList.size() - 1).getId();
            char[] charArray = id.toCharArray();
            String newID = "";
            for (int i = 1; i <= charArray.length-1 ; i++) { newID = newID + (charArray[i]); }
            int x = Integer.parseInt(newID);
            return "C"+(x+1);
        } catch (Exception e) {
            return "C1";
        }

    }
}
