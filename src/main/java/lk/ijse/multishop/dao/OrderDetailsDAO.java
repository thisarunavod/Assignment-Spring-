package lk.ijse.multishop.dao;

import lk.ijse.multishop.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetailsEntity,String> {
}
