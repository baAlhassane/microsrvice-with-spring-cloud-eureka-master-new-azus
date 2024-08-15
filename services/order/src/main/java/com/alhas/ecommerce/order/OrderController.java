package com.alhas.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

private final OrderService orderService;
@PostMapping
public ResponseEntity<Integer> createOrder(
        @RequestBody @Valid OrderRequest orderRequest
) {
return ResponseEntity.ok(orderService.createdOrder(orderRequest));
}

@GetMapping
    public ResponseEntity<List<OrderResponse>>  findAll(){
    return ResponseEntity.ok(orderService.findAll());

    }

    @GetMapping("/{oder-id}")
    public ResponseEntity<OrderResponse> findOrderById(
            @PathVariable("oder-id") Integer oderId){
    return ResponseEntity.ok(orderService.findById(oderId));

    }
}
