package com.example.webserviseprojects.repository;

import com.example.webserviseprojects.DTO.OrderDto;
import com.example.webserviseprojects.DTO.ProductDto;
import com.example.webserviseprojects.entity.Order;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository <ProductOrder, Long>{

    List<ProductOrder>findAllByOrder(OrderDto orderDto);
    List<ProductOrder>findAllByProduct(ProductDto productDto);
    Integer countAllByOrder(Order Order);

    /*
    Optional<User> findByEmail (String email);
    Optional<User> findByUsernameOrEmail(String username , String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail (String email);
*/


}
