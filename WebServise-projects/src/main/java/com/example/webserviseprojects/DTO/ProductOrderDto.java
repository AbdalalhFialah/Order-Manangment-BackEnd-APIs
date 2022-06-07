package com.example.webserviseprojects.DTO;

import com.example.webserviseprojects.entity.Order;
import com.example.webserviseprojects.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {

    private long productOrderId;
    private int quantity;

    private Double price;
    private Double  vat ;

  /*  private Product product;
    private Order order;*/

}
