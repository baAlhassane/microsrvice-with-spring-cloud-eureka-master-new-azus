package com.alhas.ecommerce.ordeline;

import com.alhas.ecommerce.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

  private final OrderLinerepository  orderLinerepository;
  private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

      var order =orderLineMapper.toOrdeline(orderLineRequest);


      return orderLinerepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
      return  orderLinerepository.findAllByOrderId(orderId)
              .stream()
              .map(orderLineMapper::toOrderLineResponse)
              .collect(Collectors.toList() );
    }
}
