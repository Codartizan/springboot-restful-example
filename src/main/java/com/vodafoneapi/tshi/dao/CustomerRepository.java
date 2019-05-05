package com.vodafoneapi.tshi.dao;


import com.vodafoneapi.tshi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tim Shi
 * @version 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
