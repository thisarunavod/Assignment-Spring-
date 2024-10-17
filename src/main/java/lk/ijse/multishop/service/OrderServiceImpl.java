package lk.ijse.multishop.service;

import lk.ijse.multishop.Util.Mapping;
import lk.ijse.multishop.dao.CustomerDAO;
import lk.ijse.multishop.dao.ItemDAO;
import lk.ijse.multishop.dao.OrderDAO;
import lk.ijse.multishop.dao.OrderDetailsDAO;
import lk.ijse.multishop.dto.impl.PlaceOrderDTO;
import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.ItemEntity;
import lk.ijse.multishop.entity.OrderDetailsEntity;
import lk.ijse.multishop.entity.OrderEntity;
import lk.ijse.multishop.entity.embedded.OrderDetailPK;
import lk.ijse.multishop.exeption.DataPersistFailedException;
import lk.ijse.multishop.exeption.NotPlacedOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Mapping mapping;

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;


    @Override
    public void placeOrder(PlaceOrderDTO orderDTO) {

        // first order entity
        if (orderDAO.existsById(orderDTO.getOrderId()))  throw new NotPlacedOrderException("Order Id is exist");

        CustomerEntity customerEntityById = customerDAO.getCustomerEntityById(orderDTO.getCustomerId());
        if (customerDAO.getCustomerEntityById(orderDTO.getCustomerId()) == null) throw new NotPlacedOrderException("Cant Find Customer");

        OrderEntity orderEntity = saveOrder(orderDTO, customerEntityById);
        if (orderEntity == null) throw new NotPlacedOrderException("Not Placed Order");

        // save on OrderDetails
        for (int i = 0; i <= orderDTO.getItemIdList().size()-1; i++) {

            double quantity = Double.valueOf(orderDTO.getQuantityList().get(i));
            String itemId = orderDTO.getItemIdList().get(i);
            ItemEntity itemEntity = updateItem( itemId, quantity );

            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            orderDetailsEntity.setOrderDetailPK(new OrderDetailPK(orderDTO.getOrderId(), itemId ));
            orderDetailsEntity.setPrice( itemEntity.getPrice() * quantity );
            orderDetailsDAO.save(orderDetailsEntity);
        }



    }

    private ItemEntity updateItem(String id, double quantity2){

        Optional<ItemEntity> tmpItem = itemDAO.findById(id);
        if (!tmpItem.isPresent())throw new NotPlacedOrderException("Cant Find Item");
        double quantity1 = itemDAO.getItemEntityById(id).getQuantity();
        if (! (quantity1 >= quantity2) ) throw new NotPlacedOrderException("Not Item Stock");
        tmpItem.get().setQuantity(quantity1-quantity2);
        return itemDAO.getItemEntityById(id);

    }



    private OrderEntity saveOrder(PlaceOrderDTO dto,CustomerEntity customer){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(dto.getOrderId());
        orderEntity.setDescription(dto.getDescription());
        orderEntity.setCustomer(customer);
        return orderDAO.save(orderEntity);
    }
}
