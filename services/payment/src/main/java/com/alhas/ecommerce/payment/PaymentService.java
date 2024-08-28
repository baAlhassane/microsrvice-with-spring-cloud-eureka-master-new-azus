package com.alhas.ecommerce.payment;

import com.alhas.ecommerce.notification.NotificationProducer;
import com.alhas.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

/**
 * private final PaymentRepository paymentRepository;
 */
    private final PaymentRepository paymentRepository;
 private final PaymentMapper paymentMapper;
 private final NotificationProducer notificationProducer;


    public Integer createPayment(PaymentRequest paymentRequest) {

        var payment=this.paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        this.notificationProducer.sendNotification(new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstname(),
                paymentRequest.customer().lastname(),
                paymentRequest.customer().email()
        ));
        return payment.getId();

    }
}
