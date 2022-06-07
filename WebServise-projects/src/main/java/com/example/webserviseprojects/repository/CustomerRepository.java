package com.example.webserviseprojects.repository;

import com.example.webserviseprojects.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Long> {
}
