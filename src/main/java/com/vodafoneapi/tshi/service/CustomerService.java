package com.vodafoneapi.tshi.service;

import com.vodafoneapi.tshi.dao.CustomerRepository;
import com.vodafoneapi.tshi.entity.Customer;
import com.vodafoneapi.tshi.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vodafoneapi.tshi.util.Util.getNullPropertyNames;

/**
 * @author Tim Shi
 * @version 1.0
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * List all customer details
     * @return Customer entity list
     */
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    /**
     * Add new customer
     * @param customer New customer entity
     */
    public void addCustomer(Customer customer) {
            customerRepository.save(customer);
    }

    /**
     * Delete customer
     * @param id Customer ID
     * @throws NotFoundException If the customer is not found, throw exception
     */
    public void deleteCustomer(Integer id) throws NotFoundException {
        Customer currentInstance = customerRepository.findById(id).orElse(null);
        if (currentInstance != null) {
            customerRepository.deleteById(id);
        } else {
            throw new NotFoundException("Customer " + id + " is not exist!");
        }
    }

    /**
     * Update customer details
     * @param id Customer ID
     * @param customer Customer entity to be updated
     * @throws NotFoundException If the customer is not found, throw exception
     */
    public void updateCustomer(Integer id, Customer customer) throws NotFoundException{
        Customer currentInstance = customerRepository.findById(id).orElse(null);
        if (currentInstance != null) {
            String[] nullPropertyNames = getNullPropertyNames(customer);
            BeanUtils.copyProperties(customer, currentInstance, nullPropertyNames);
        } else {
            throw new NotFoundException("Customer " + id + " is not exist!");
        }
        customerRepository.save(currentInstance);
        //customerRepository.save(customer);
    }

    /**
     * Query customer details
     * @param id Customer ID
     * @return Customer entity
     * @throws NotFoundException If the customer is not found, throw exception
     */
    public Customer getCustomer(Integer id) throws NotFoundException {
        Customer currentInstance = customerRepository.findById(id).orElse(null);
        if (currentInstance == null) {
            throw new NotFoundException("Customer " + id + " is not exist!");
        }
        return currentInstance;
    }



}
