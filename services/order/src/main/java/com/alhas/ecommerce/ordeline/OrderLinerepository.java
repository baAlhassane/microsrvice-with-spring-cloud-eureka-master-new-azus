package com.alhas.ecommerce.ordeline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLinerepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderid(Integer orderId);
}
