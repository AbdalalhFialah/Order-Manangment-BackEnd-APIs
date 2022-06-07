package com.example.webserviseprojects.DTO;

import com.example.webserviseprojects.entity.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

// This Class Represent the Customer Dto
public class CustomerDto {
    private Long id;
    @NotEmpty
    @Size(min = 3 , message = "Customer First Name Must Have At Lest 3 Character")
    private String firstName;
    @NotEmpty // The annotated element must not be null nor empty.
    @Size(min = 3 , message = "Customer Last Name Must Have At Lest 3 Character") // The annotated element size must be between the specified boundaries
    private String lastName;
    private LocalDate dob;


    private Set<Order> orders = new HashSet<>() ;

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", orders=" + orders +
                '}';
    }
}
