package com.example.webserviseprojects.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/*
* This Class to Represent Customer Entity
*
* */
@Setter
@Getter
// @Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "customers") // Table annotation specifies the name of the database table to be used for mapping.
@AllArgsConstructor //  generates a constructor with 1 parameter for each field in the class
@NoArgsConstructor // will generate a constructor with no parameters
public class Customer {
    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @Column(name = "id")
    @ApiModelProperty(value = "This Id of Customer , and it's auto-Increment" , required = false)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private Long id;
    @ApiModelProperty(value = "This First Name of Customer " , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "firstName")
    private String firstName;

    @ApiModelProperty(value = "This Last Name of Customer " , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "lastName")
    private String lastName;

    @ApiModelProperty(value = "This the Date " , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "dob") //used for Adding the column the name in the table of a particular MySQL database.
    private LocalDate dob;
 // TODO Change Date To LOCALDATE

    @OneToMany (mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", orders=" + orders +
                '}';
    }
}
