package com.example.homework_spring_course.controller;

import com.example.homework_spring_course.entity.Customer;
import com.example.homework_spring_course.repository.CustomerRepository;
import com.example.homework_spring_course.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok().body(service.addCustomer(customer));
    }

    @PutMapping
    ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok().body(service.updateCustomer(customer));
    }

    @GetMapping("all")
    ResponseEntity<List<Customer>> getCustomer(){
        return ResponseEntity.ok().body(service.findAllCustomers());
    }

    @GetMapping("{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findCustomerById(id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<MessageResponse> deleteCustomer(@PathVariable Long id){
        service.deleteCustomer(id);
        return ResponseEntity.ok().body(successResponse());
    }

    private MessageResponse successResponse() {
        return new MessageResponse();
    }


    static class MessageResponse {
        int status = 200;
        String message = "success";
    }
}
