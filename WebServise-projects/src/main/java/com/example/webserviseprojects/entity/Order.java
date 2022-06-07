package com.example.webserviseprojects.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
// @Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
@Table(name="orders") // Table annotation specifies the name of the database table to be used for mapping.
@AllArgsConstructor // generates a constructor with 1 parameter for each field in the class
@NoArgsConstructor // will generate a constructor with no parameters
/*
 * This Class to Represent Order Entity
 *
 * */
public class Order {
    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @ApiModelProperty(value = "Id Of Order " , required = false)
    private Long id;

    @Column //used for Adding the column the name in the table of a particular MySQL database.
    @ApiModelProperty(value = "Local Date Time For Order" , required = true)
    private LocalDateTime dateTime;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Set<ProductOrder> productOrders =  new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_Id" , nullable = false) // marks a column as a join column for an entity association or an element collection.

    private Customer customer;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", customer=" + customer +
                '}';
    }
}
