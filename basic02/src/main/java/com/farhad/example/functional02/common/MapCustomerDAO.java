package com.farhad.example.functional02.common;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
// import java.util.Random;

@Slf4j
public class MapCustomerDAO implements CustomerDAO {
    
    private Map<Long,Customer> repo = new HashMap<>();

    // private Random  rand = new Random();

    public Customer create(Customer customer) {
        // long id = rand.nextLong();
        if (repo.containsKey(customer.getCustomerId()))
            throw new CustomerExistException();

        log.info("Create Customer: {}",customer);
        repo.put(customer.getCustomerId(), customer);
        return customer;
    }

    public Customer findCustomerById(long id) {
        return repo.get(id);
    }
}
