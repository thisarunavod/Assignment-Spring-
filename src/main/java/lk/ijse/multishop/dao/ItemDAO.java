package lk.ijse.multishop.dao;

import lk.ijse.multishop.entity.CustomerEntity;
import lk.ijse.multishop.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO  extends JpaRepository<ItemEntity,String> {

    ItemEntity getItemEntityById(String id);
}
