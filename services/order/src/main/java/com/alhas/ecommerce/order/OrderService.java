package com.alhas.ecommerce.order;

import com.alhas.ecommerce.customer.CustomerClient;
import com.alhas.ecommerce.exception.BusinessException;
import com.alhas.ecommerce.kafka.OrderConfirmation;
import com.alhas.ecommerce.kafka.OrderProducer;
import com.alhas.ecommerce.ordeline.OrderLineRequest;
import com.alhas.ecommerce.ordeline.OrderLineService;
import com.alhas.ecommerce.payment.PaymentClient;
import com.alhas.ecommerce.payment.PaymentRequest;
import com.alhas.ecommerce.product.ProductClient;
import com.alhas.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper ;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public Integer createdOrder(OrderRequest orderRequest) {
        //1- cheqk thhe customer -> we use openFeign
  var customer=this.customerClient.findCustomerById(orderRequest.customerId())
          .orElseThrow(()-> new BusinessException("Can not create order :: No customer exists with customerId = "+orderRequest.customerId() ));


        //2- Purchase the products -> microservuce
      var purchaseProducts=   this.productClient.purchaseProducts(orderRequest.products());

        //3- persist order
        var order=this.orderRepository.save(orderMapper.toOrder(orderRequest));


        //4- persist orderline
        for(PurchaseRequest purchaseRequest: orderRequest.products()){

            orderLineService.saveOrderLine(
                    new OrderLineRequest(null,
                            order.getId() ,
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
                    )
            );

        }

        //5- start payment process
        var paymentRequest=new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
paymentClient.requestOrderPayment( paymentRequest);

        //6- sent order confirmation ->notification we use kafka.
orderProducer.sendOrderConfirmation(
        new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchaseProducts
        )
);

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer oderId) {
        return orderRepository.findById(oderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException("No order found with id = " + oderId));
    }


}
