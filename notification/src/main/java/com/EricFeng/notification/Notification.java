package com.EricFeng.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_id_Sequence",
            sequenceName = "notification_id_Sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_Sequence"

    )
    private Integer id;
    private Integer customerID;
    private String msg;
}
