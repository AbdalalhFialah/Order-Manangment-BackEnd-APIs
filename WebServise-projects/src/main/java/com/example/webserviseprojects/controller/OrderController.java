package com.example.webserviseprojects.controller;

import com.example.webserviseprojects.DTO.CustomerDto;
import com.example.webserviseprojects.DTO.OrderDto;
import com.example.webserviseprojects.entity.Order;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "CRUD REST APIs for Order Resources")

public class OrderController {

    private OrderServiceImpl orderService ;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<OrderDto> getAllCustomer (){
        return orderService.getAllOrders();
    }
   @GetMapping("orders/test")
    public List<OrderDto> getSCustomers() {
        List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
        for (int i = 0; i < 2; i++) {
            orderDtoList.add(new OrderDto(i , LocalDateTime.now()));
        }
        return orderDtoList;
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })

    public ResponseEntity<String> deleteOrder (@PathVariable(name = "id") long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Delete Order Was Done Successfully",HttpStatus.OK);
    }


    //
    @PostMapping("/customers/{customerId}/orders")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })

    public ResponseEntity<OrderDto> createOrder (@Valid @RequestBody OrderDto orderDto
            ,@PathVariable(value = "customerId") long customerId){
        System.out.println(orderDto);
        return new ResponseEntity<>(orderService.createOrderRecords(orderDto,customerId), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerId}/orders")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })

    public List<OrderDto> getOrderByCustomer(@PathVariable(value = "customerId") long customerId){

        return  orderService.getOrderByCustomerId(customerId);

    }
    @GetMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderDto>getOrderById (@PathVariable(value = "customerId") Long customerId ,
                                                 @PathVariable(value = "orderId") Long orderId ){
        OrderDto orderDto = orderService.getOrderById(orderId,customerId);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}/orders/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })

    public ResponseEntity<OrderDto> updateOrder (@RequestBody OrderDto orderDto ,
                                                 @PathVariable(value = "customerId") Long customerId ,
                                                 @PathVariable(value = "orderId") Long orderId ){
        System.out.println(orderDto);
        OrderDto updateOrder = orderService.updateOrder(customerId,orderId,orderDto);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);

    }
    @DeleteMapping("/customers/{customerId}/orders/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })

    public ResponseEntity<String> deleteOrder ( @PathVariable(value = "customerId") Long customerId ,
                                                @PathVariable(value = "orderId") Long orderId){
        orderService.deleteOrder(customerId,orderId);
        return new ResponseEntity<>("Delete Order Was Done Successfully",HttpStatus.OK);

    }

}
