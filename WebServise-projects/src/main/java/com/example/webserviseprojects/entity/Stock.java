package com.example.webserviseprojects.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Table(name="stocks") // Table annotation specifies the name of the database table to be used for mapping.
@Entity //  This annotation specifies that the class is an entity and is mapped to a database table
@AllArgsConstructor // generates a constructor with 1 parameter for each field in the class
@NoArgsConstructor // will generate a constructor with no parameters
/*
 * This Class to Represent Stock Entity
 *
 * */
public class Stock {
    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @ApiModelProperty(value = "This Id of Customer " , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private Long id;
    @Column
    @ApiModelProperty(value = "This quantity of Stock " , required = true) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private int quantity;
    @Column //used for Adding the column the name in the table of a particular MySQL database.
    @ApiModelProperty(value = "This Local Date Time of Stock " , required = true) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id" , nullable = false) //productId to provide foreign key column
    private Product product;


}
