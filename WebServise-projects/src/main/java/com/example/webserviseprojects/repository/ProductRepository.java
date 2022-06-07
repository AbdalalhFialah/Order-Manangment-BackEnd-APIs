package com.example.webserviseprojects.repository;

import com.example.webserviseprojects.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
