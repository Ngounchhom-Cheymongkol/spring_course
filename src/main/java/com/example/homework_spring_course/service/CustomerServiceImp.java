package com.example.homework_spring_course.service;

import com.example.homework_spring_course.entity.Customer;
import com.example.homework_spring_course.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer req) {
        req.setCreateAt(new Date());
        req.setStatus(true);
        return customerRepository.save(req);
    }

    @Override
    public Customer updateCustomer(Customer req) {
        Customer customer = findCustomerById(req.getId());
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setPhone(req.getPhone());
        customer.setUpdateAt(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found with id: " + id));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
