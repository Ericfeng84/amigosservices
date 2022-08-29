package com.EricFeng.notification.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumerKafka {

    @KafkaListener(topics = "notification", groupId = "notification")
    public void listenGroupFoo(String message) {
        log.info("Received Kafka Message {}  " , message);
    }


}
