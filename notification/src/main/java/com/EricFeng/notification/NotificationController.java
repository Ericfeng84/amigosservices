package com.EricFeng.notification;

import com.EricFeng.clients.notification.NewNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")

public record NotificationController(NotificationService notificationService) {

    @PostMapping
    public void CreateNotification(@RequestBody NewNotification newNotification){
        log.info("New Notification {} Received",newNotification);
        notificationService.saveNotification(newNotification);
    };
}
