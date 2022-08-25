package com.EricFeng.notification;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueues() {
        return notificationQueues;
    }

    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueues;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueues);
    }

    @Bean
    public Binding internalNotificationBinding(){
        return BindingBuilder.bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);

    }


}
