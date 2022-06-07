package com.example.webserviseprojects.service;

import com.example.webserviseprojects.DTO.OrderDto;
import com.example.webserviseprojects.DTO.ProductOrderDto;

import java.util.List;

public interface ProductOrderService {
    ProductOrderDto createProductOrderRecords (ProductOrderDto productOrderDto);
    List<ProductOrderDto> getAllproductOrder ();
    ProductOrderDto getProductOrderById (long id);
    ProductOrderDto updateProductOrder (ProductOrderDto productOrderDto , long id);
    void deleteProductOrderById (long id);

    // TODO Implement This Method
    ProductOrderDto createProductOrder (ProductOrderDto productOrderDto);
     Integer findOrder(ProductOrderDto productOrderDto);




}
