package com.alhas.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class customerMapping {
    public Customer toCustomer(CustomerRequest request) {
        if(request==null){
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }
}
