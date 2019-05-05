package com.vodafoneapi.tshi.controller;

import com.vodafoneapi.tshi.entity.Customer;
import com.vodafoneapi.tshi.exceptions.NotFoundException;
import com.vodafoneapi.tshi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Tim Shi
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomerList() {
        return customerService.getCustomerList();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
            customerService.addCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerRestful(@PathVariable("id") Integer id) {
        try {
            customerService.deleteCustomer(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) throws NotFoundException {
        customerService.updateCustomer(id, customer);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("id") Integer id) {
        try {
            return customerService.getCustomer(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
