package com.example.webserviseprojects.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;
/*
* The Class to Represent The User Entity ,
* Contains Basic Attribute
*  */
@Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})}) // Table annotation specifies the name of the database table to be used for mapping.

public class User {
    @Id // To make this Attribute is the pk of entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the primary keys values.

    @ApiModelProperty(value = "This ID Of User " , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private long id ;
    @ApiModelProperty(value = "This Name Of User " , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private String name;
    @ApiModelProperty(value = "This UserName Of User " , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private String username;
    @Email
    @ApiModelProperty(value = "Email Of User " , required = false) // allows us to control Swagger-specific definitions such as description (value), name, data type, example values, and allowed values for the model properties.
    private String email;
    @ApiModelProperty(value = "Password UserName Of User " , required = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role>roles;

}
