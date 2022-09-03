package com.EricFeng.notification.rabbitmq;

import com.EricFeng.clients.notification.NewNotification;
import com.EricFeng.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NewNotification newNotification){
        log.info("Consumed {} from queue",newNotification);
        notificationService.saveNotification(newNotification);
    }



}
