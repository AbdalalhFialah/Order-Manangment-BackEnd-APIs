package com.example.webserviseprojects.service;

import com.example.webserviseprojects.DTO.OrderDto;
import com.example.webserviseprojects.DTO.StockDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrderRecords ( OrderDto orderDto , long customerId);
    List<OrderDto> getOrderByCustomerId (long customerId);
    OrderDto updateOrder (long customerId , long orderId ,OrderDto orderRequest);
    OrderDto getOrderById (long orderId ,long customerId);
    List<OrderDto> getAllOrders ();
 /*   OrderDto updateOrder (OrderDto orderDto , long id);*/
    void deleteOrderById (long id);
    void deleteOrder (long customerId , long orderId);


}
