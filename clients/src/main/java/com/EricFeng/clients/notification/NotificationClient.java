package com.EricFeng.clients.notification;

import com.EricFeng.clients.fraud.CheckFraudResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {

        @PostMapping("api/v1/notification")
        void CreateNotification(@RequestBody NewNotification newNotification);
}


