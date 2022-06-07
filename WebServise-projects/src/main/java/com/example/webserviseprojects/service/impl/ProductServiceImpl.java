package com.example.webserviseprojects.service.impl;

import com.example.webserviseprojects.DTO.CustomerDto;
import com.example.webserviseprojects.DTO.ProductDto;
import com.example.webserviseprojects.entity.Customer;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.exception.ResourceNotFoundException;
import com.example.webserviseprojects.repository.CustomerRepository;
import com.example.webserviseprojects.repository.ProductRepository;
import com.example.webserviseprojects.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public ProductDto createProductRecords(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);
        ProductDto productDto1 = mapToDto(newProduct);

        return productDto1;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> list = productRepository.findAll();

        return list.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product =  productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product","Id",id)
        );
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","Id",id));

        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setReference(productDto.getReference());
        product.setVat(productDto.getVat());
        product.setStockable(productDto.isStockable());

        Product updateProduct = productRepository.save(product);
        return mapToDto(updateProduct);
    }

    @Override
    public void deleteProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","Id",id));
        productRepository.delete(product);

    }
    // Convert From  Entity to DTO
    private ProductDto mapToDto (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setReference(product.getReference());
        productDto.setVat(product.getVat());
        productDto.setStockable(product.isStockable());

        return productDto;
    }
    // Convert From DTO to Entity
    private Product mapToEntity (ProductDto productDto){

        Product product = new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setReference(productDto.getReference());
        product.setVat(productDto.getVat());
        product.setStockable(productDto.isStockable());

        return product;
    }
}
