package com.ericFeng.customer;

import com.EricFeng.clients.fraud.CheckFraudResponse;
import com.EricFeng.clients.fraud.FraudClient;
import com.EricFeng.clients.notification.NewNotification;
import com.EricFeng.clients.notification.NotificationClient;
import com.ericfeng.amqp.RabbitMQMessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer,
                              NotificationClient notificationClient,
                              KafkaTemplate<String, String> kafkaTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer=Customer.builder().
                firstName(request.firstName())
                .lastName(request.LastName())
                .email(request.email()).
                build();
        //todo check if email valid
        //todo check email is not token
        //todo store customer in db
        customerRepository.saveAndFlush(customer);
//        check Fraud
//        CheckFraudResponse checkFraudResponse = restTemplate.getForObject(
//                "http://FRAUD/.api/v1/fraud/{customerID}",
//                CheckFraudResponse.class, customer.getId()
//        );

        CheckFraudResponse checkFraudResponse = fraudClient.CheckFraudHistory(customer.getId());

        if (checkFraudResponse.isFraud()){
            throw new IllegalStateException("Fraud");
        }

        NewNotification newNotification=
              new NewNotification(customer.getId(),"test2");

//        rabbitMQMessageProducer.publish(newNotification,
//                "internal.exchange",
//                "internal.notification.routing-key");


        kafkaTemplate.send("notification", newNotification.toString());



    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
