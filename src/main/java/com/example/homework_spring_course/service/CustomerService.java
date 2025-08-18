package com.example.homework_spring_course.service;

import com.example.homework_spring_course.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer req);
    Customer updateCustomer(Customer req);
    void deleteCustomer(Long id);
    Customer findCustomerById(Long id);
    List<Customer> findAllCustomers();
}
