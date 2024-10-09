package lk.ijse.multishop.dao;

import lk.ijse.multishop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerDAO extends JpaRepository<CustomerEntity,String> {}
