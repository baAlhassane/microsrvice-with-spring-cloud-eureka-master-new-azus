package com.alhas.ecommerce.payment;

import com.alhas.ecommerce.payment.notification.NotificationProducer;
import com.alhas.ecommerce.payment.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

private final PaymentRepository paymentRepository;
 private final PaymentMapper paymentMapper;
 private NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {

        var payment=paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amoubn(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstname(),
                paymentRequest.customer().lastname(),
                paymentRequest.customer().email()
        ));
        return payment.getId();

    }
}
