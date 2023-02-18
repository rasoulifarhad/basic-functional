package com.farhad.example.functional02.common;

public interface CustomerDAO {

    public Customer create(Customer customer) ;

    public Customer findCustomerById(long id) ;

    public static CustomerDAO defaultDao() {
        return new MapCustomerDAO();

    }
    
}
