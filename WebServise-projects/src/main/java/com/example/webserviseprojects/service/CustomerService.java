package com.example.webserviseprojects.service;

import com.example.webserviseprojects.DTO.CustomerDto;

import java.util.List;

public interface CustomerService {
     CustomerDto createCustomerRecords (CustomerDto customerDto);
     List<CustomerDto> getAllCustomers ();
     CustomerDto getCustomerById (long id);
     CustomerDto updateCustomer (CustomerDto customerDto , long id);
     void deleteCustomerById (long id);


}
