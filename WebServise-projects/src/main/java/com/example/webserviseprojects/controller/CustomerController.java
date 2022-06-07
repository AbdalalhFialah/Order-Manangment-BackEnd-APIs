package com.example.webserviseprojects.controller;

import com.example.webserviseprojects.DTO.CustomerDto;
import com.example.webserviseprojects.entity.Customer;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.service.impl.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
@Api(description = "CRUD REST APIs for Customer Resources")

public class CustomerController {
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto>getAllCustomer (){
        return customerService.getAllCustomers();
    }



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Customer.class, responseContainer = "Response") })

    public ResponseEntity<CustomerDto> createCustomer (@Valid @RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.createCustomerRecords(customerDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById (@PathVariable(name = "id") long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Customer.class, responseContainer = "Response") })

    public ResponseEntity<CustomerDto> updateCustomer (@Valid @RequestBody CustomerDto customerDto , @PathVariable (name ="id") long id){
        CustomerDto customerDto1 = customerService.updateCustomer(customerDto,id);
        return new ResponseEntity<>(customerDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Customer.class, responseContainer = "Response") })

    public ResponseEntity<String> deleteCustomer (@PathVariable(name = "id") long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Delete Customer Was Done Successfully",HttpStatus.OK);
    }
}
