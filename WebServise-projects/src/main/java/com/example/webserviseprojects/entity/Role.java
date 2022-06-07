package com.example.webserviseprojects.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "roles") // Table annotation specifies the name of the database table to be used for mapping.
/*
 * This Class to Represent Role Entity
 *
 * */
public class Role {
    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @ApiModelProperty(value = "This the id Of Role" , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private long id;
    @Column(length  =60) //used for Adding the column the name in the table of a particular MySQL database.
    @ApiModelProperty(value = "This the Name Of Role" , required = true)// allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private String name;

}
