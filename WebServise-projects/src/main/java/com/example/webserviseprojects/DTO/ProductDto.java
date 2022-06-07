package com.example.webserviseprojects.DTO;

import com.example.webserviseprojects.entity.ProductOrder;
import com.example.webserviseprojects.entity.Stock;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
// @Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    @NotEmpty
    @Size(min = 3 , message = "Product Name Must Have At Lest 3 Character")
    private String name;

    @NotEmpty
    @Size(min = 3 , message = "Product reference Must Have At Lest 3 Character")
    private String reference;

    private Double price;

    private Double  vat ;

    private boolean stockable;


/*
    private Set<Stock> stockSet =  new HashSet<>();;
    private Set<ProductOrder> productOrders = new HashSet<>();;
*/

}
