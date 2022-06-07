package com.example.webserviseprojects.controller;

import com.example.webserviseprojects.DTO.CustomerDto;
import com.example.webserviseprojects.DTO.ProductOrderDto;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.service.impl.ProductOrderSerivceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productOrders")
@Api(description = "CRUD REST APIs for Product-Order Resources")

public class ProductOrderController {

    private ProductOrderSerivceImpl productOrderSerivce;

    public ProductOrderController(ProductOrderSerivceImpl productOrderSerivce) {
        this.productOrderSerivce = productOrderSerivce;
    }
     @GetMapping("/test")
 public List<ProductOrderDto> getSCustomers() {
     List<ProductOrderDto> productOrderDtoArrayList = new ArrayList<ProductOrderDto>();
     for (int i = 0; i < 2; i++) {
         productOrderDtoArrayList.add(new ProductOrderDto(i , i*10 , 3.56*i , 4.76*i ));
     }
     return productOrderDtoArrayList;
 }
    @GetMapping
    public List<ProductOrderDto> getAllCustomer (){
        return productOrderSerivce.getAllproductOrder();
    }



    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })


    public ResponseEntity<ProductOrderDto> createCustomer (@Valid @RequestBody ProductOrderDto productOrderDto){
        return new ResponseEntity<>(productOrderSerivce.createProductOrderRecords(productOrderDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductOrderDto> getCustomerById (@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productOrderSerivce.getProductOrderById(id));
    }
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    public ResponseEntity<ProductOrderDto> updateCustomer (@Valid @RequestBody ProductOrderDto productOrderDto , @PathVariable (name ="id") long id){
        ProductOrderDto productOrderDto1 = productOrderSerivce.updateProductOrder(productOrderDto,id);
        return new ResponseEntity<>(productOrderDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    public ResponseEntity<String> deleteCustomer (@PathVariable(name = "id") long id){
        productOrderSerivce.deleteProductOrderById(id);
        return new ResponseEntity<>("Delete Product Order Was Done Successfully",HttpStatus.OK);
    }


}

