package com.alhas.ecommerce.kafka;

import com.alhas.ecommerce.email.EmailService;
import com.alhas.ecommerce.kafka.order.OrderConfirmation;
import com.alhas.ecommerce.kafka.payment.PaymentConfirmation;
import com.alhas.ecommerce.notification.Notification;
import com.alhas.ecommerce.notification.NotificationRepository;
import com.alhas.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

   // private final KafkaConsumer<String, String> consumer;
    private final NotificationRepository  notificationRepository;
    private final EmailService emailService;

    // private final EmailService emailService;
@KafkaListener(topics = "payment-topic")
    public void consumerPaymentSucessNotification(
            PaymentConfirmation paymentConfirmation) throws MessagingException {
    log.info(String.format("Cusuming the message from payment topic Topic ::: %s ",paymentConfirmation));
    notificationRepository.save(Notification.builder()
                    .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                    .notificationDate(LocalDateTime.now())
                    .paymentConfirmation(paymentConfirmation)
            .build()
    );

    //to do send email
    var customerName=paymentConfirmation.customerFistName()+" "+paymentConfirmation.customerLastName();
    emailService.sendPaymentSuccessEmail(
            paymentConfirmation.customerEmail(),
            customerName,
            paymentConfirmation.amount(),
            paymentConfirmation.orderreference()
    );


}


    @KafkaListener(topics = "order-topic")
    public void orderConfirmationNotification(
            OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Cusuming the message from payment topic Topic ::: %s ",orderConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.ODER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build()
        );

        //to do send email
        var customerName=orderConfirmation.customer().firstname()+" "+orderConfirmation.customer().firstname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );


    }

}
