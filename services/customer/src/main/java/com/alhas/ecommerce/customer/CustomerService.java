package com.alhas.ecommerce.customer;

import com.alhas.ecommerce.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final customerMapping customerMapping;
    public String createCustomer(CustomerRequest request) {
        var customer=customerRepository.save(customerMapping.toCustomer(request));
        return customer.getId();
    }


    public void updateCustomer(CustomerRequest customerRequest) {
        var customer=customerRepository.findById(customerRequest.id())
                .orElseThrow( ()-> new CustomerNotFoundException(
                        format("can not update customer :: No found customer with the provide ID = %s ", customerRequest.id())
                ));

        mergeCustomer(customer,customerRequest);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname()))
            customer.setFirstname(customerRequest.firstname());

        if(StringUtils.isNotBlank(customerRequest.lastname()))
            customer.setLastname(customerRequest.lastname());

        if(StringUtils.isNotBlank(customerRequest.email()))
            customer.setEmail( customerRequest.email());

        if(customerRequest.address()!=null)
            customer.setAddress(customerRequest.address());
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
