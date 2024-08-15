package com.alhas.ecommerce.payment;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "product-service",
        url ="{application.config.payment-url}"
)
public interface PaymentClient {


    @PostMapping
    Integer requestOrderPayment(
            @RequestBody  PaymentRequest paymentrequest);

}
