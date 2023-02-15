package com.example.hw28.Repository;

import com.example.hw28.Model.MyUser;
import com.example.hw28.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Orders findOrdersById(Integer id);
    Orders findOrdersByStatus(String status);
    List<Orders> findAllByMyUserId(Integer id);
    List<Orders> findAllByProductId(Integer id);
    Orders findOrdersByMyUserId(Integer id);
    List<Orders> findAllByMyUser(MyUser myUser);
}
