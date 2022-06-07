package com.example.webserviseprojects.service;

import com.example.webserviseprojects.DTO.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProductRecords (ProductDto productDto);
    List<ProductDto> getAllProducts ();
    ProductDto getProductById (long id);
    ProductDto updateProduct (ProductDto productDto , long id);
    void deleteProductById (long id);
}
