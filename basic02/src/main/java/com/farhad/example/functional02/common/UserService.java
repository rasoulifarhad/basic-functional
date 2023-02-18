package com.farhad.example.functional02.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(staticName = "of")
@Slf4j
public class UserService {

    private final CustomerDAO customerDao;

    public long attachCustomerInfo(User user , String type) {
        Customer customer = Customer.of(user.getUserId()) ;
        log.info("Customer to Save: {}",customer);
        Customer createdCustomer =  this.customerDao.create(customer);
        log.info("Created Customer : {}",createdCustomer);

        return createdCustomer.getCustomerId();
    }
    
}
