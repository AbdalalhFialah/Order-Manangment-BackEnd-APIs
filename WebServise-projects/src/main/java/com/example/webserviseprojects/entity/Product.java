package com.example.webserviseprojects.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//@Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Getter
@Setter
@Entity // // This annotation specifies that the class is an entity and is mapped to a database table
@AllArgsConstructor //  generates a constructor with 1 parameter for each field in the class
@NoArgsConstructor // will generate a constructor with no parameters
@Table(name="products") // Table annotation specifies the name of the database table to be used for mapping.
/*
 * This Class to Represent Customer Entity
 *
 * */
public class Product {

    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "This Id of Products" , required = false)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private Long id;
    @ApiModelProperty(value = "This Name of Products" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "This Reference of Products" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "reference")
    private String reference;

    @ApiModelProperty(value = "This Price of Products" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "price") // used for Adding the column the name in the table of a particular MySQL database.
    private Double price;
    @ApiModelProperty(value = "This Vat of Products" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "vat") //used for Adding the column the name in the table of a particular MySQL database.
    private Double  vat ;
    @ApiModelProperty(value = "This Stockable of Products" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    @Column(name = "stockable")
    private boolean stockable;



    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stock> stock = new HashSet<>();

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", price=" + price +
                ", vat=" + vat +
                ", stockable=" + stockable +
                ", stock=" + stock +
                '}';
    }
}
