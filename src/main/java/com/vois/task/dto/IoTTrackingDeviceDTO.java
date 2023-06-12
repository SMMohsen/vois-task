package com.vois.task.dto;

import com.vois.task.enumuration.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IoTTrackingDeviceDTO {

    private String id;

    private Status status;

    private String pin;

    private Integer temperature;
}
