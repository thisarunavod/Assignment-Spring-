package lk.ijse.multishop.dao;

import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<OrderEntity,String> {

}
