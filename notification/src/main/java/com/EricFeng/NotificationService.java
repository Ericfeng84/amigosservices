package com.EricFeng;

import com.EricFeng.clients.notification.NewNotification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    public void saveNotification(NewNotification newNotification){
        log.info("New Notification {} service Received",newNotification);

        notificationRepository.save(
                Notification.builder()
                        .customerID(newNotification.customerID())
                        .msg(newNotification.msg())
                .build()
        );
    }
}
