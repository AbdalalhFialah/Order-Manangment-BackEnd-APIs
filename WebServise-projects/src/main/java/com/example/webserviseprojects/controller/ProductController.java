package com.example.webserviseprojects.controller;

import com.example.webserviseprojects.DTO.ProductDto;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

@Api(description = "CRUD REST APIs for Product Resources")
@RestController
@RequestMapping("/api")
public class ProductController {


    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @ApiOperation(value = " Get Dummy Data and Shown It When Test The Controller ")
    @GetMapping("/products/test")
    public List<ProductDto> getProduct() {
        List<ProductDto> productList = new ArrayList<ProductDto>();
        for (int i = 0; i < 2; i++)
            productList.add(new ProductDto(Integer.toUnsignedLong(i), "Iphone", "This Product about", 120.232, 23.3, true));

        return productList;
    }


    @ApiOperation(value = "Get All Product REST API")
    @GetMapping("/products")
    public List<ProductDto> getAllProduct() {
        return productService.getAllProducts();
    }


    @ApiOperation(value = "Create new Records of Product REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.createProductRecords(productDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get Single Product By ID REST API ")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    @ApiOperation(value = "Update Product BY ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable(name = "id") long id) {
        ProductDto productDto1 = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    @ApiOperation(value = "Delete Product BY ID REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Delete Product Was Done Successfully", HttpStatus.OK);
    }

}
