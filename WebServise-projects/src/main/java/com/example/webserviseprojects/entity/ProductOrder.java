package com.example.webserviseprojects.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor // generates a constructor with 1 parameter for each field in the class
@NoArgsConstructor // will generate a constructor with no parameters
@Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Entity // // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "Product_order") // Table annotation specifies the name of the database table to be used for mapping.
/*
 * This Class to Represent Product-Order Entity
 *
 * */
public class ProductOrder {

    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @ApiModelProperty(value = "This the product Order Id" , required = false)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private Long productOrderId;

    @Column // used for Adding the column the name in the table of a particular MySQL database.
    @ApiModelProperty(value = "This the Quantity of Product Order " , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private int quantity;
    @Column //used for Adding the column the name in the table of a particular MySQL database.
    @ApiModelProperty(value = "This the Price Product Order" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private double price;
    @Column
    @ApiModelProperty(value = "This the Vat Product Order" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private double vat;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id",nullable = false) // product_Id to provide foreign key column
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_Id",nullable = false) // order_Id to provide foreign key column
    private Order order;

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productOrderId=" + productOrderId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", vat=" + vat +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
