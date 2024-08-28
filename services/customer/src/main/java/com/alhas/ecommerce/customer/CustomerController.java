package com.alhas.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody
            @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(this.customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<?> updateCustoer(
            @RequestBody
            @Valid CustomerRequest customerRequest
            ){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }


    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existCustomerById(
           @PathVariable( "customer-id") String custommerId ){
        return  ResponseEntity.ok(customerService.existById(custommerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(
            @PathVariable( "customer-id") String custommerId ){
        return  ResponseEntity.ok(customerService.findCustomerById(custommerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomerById(
            @PathVariable("customer-id") String customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();

    }
}
