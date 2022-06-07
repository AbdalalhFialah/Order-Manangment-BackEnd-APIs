package com.example.webserviseprojects.repository;

import com.example.webserviseprojects.entity.Customer;
import com.example.webserviseprojects.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository <Order , Long> {
    List<Order> findByCustomerId(long customerId);

}
