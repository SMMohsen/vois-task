package com.vois.task.model;

import com.vois.task.enumuration.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document("iot-devices")
public class IoTTrackingDevice {

    @Id
    private String id = UUID.randomUUID().toString();

    private Status status = Status.READY;

    @Indexed(unique = true)
    private String pin;

    private Integer temperature = 1;
}
