package com.alhas.ecommerce.ordeline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordr-lines")
@RequiredArgsConstructor
public class OrderlineController {

public final OrderLineService orderLineService;
@GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable Integer orderId) {

    return  ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
}
}
