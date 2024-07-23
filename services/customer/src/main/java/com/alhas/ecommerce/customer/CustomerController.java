package com.alhas.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
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
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }
}
