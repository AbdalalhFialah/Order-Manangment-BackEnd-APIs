package com.example.webserviseprojects.repository;

import com.example.webserviseprojects.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByProductId(long productId);

}
