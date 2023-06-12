package com.vois.task.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;



@Getter
@Setter
public class IoTCreateDeviceDTO {

    @Length(min = 7, max = 7)
    private String pin;
}
