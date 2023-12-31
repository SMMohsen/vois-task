package com.vois.task.controller;

import com.vois.task.dto.ApiResponse;
import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.service.DeviceConfigurationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iot/configuration")
public class DeviceConfigurationController {

    private final DeviceConfigurationService deviceConfigurationService;

    public DeviceConfigurationController(DeviceConfigurationService deviceConfigurationService) {

        this.deviceConfigurationService = deviceConfigurationService;
    }

    @PostMapping("/{id}")
    public ApiResponse configureDevice(@PathVariable String id) {

        IoTTrackingDeviceDTO configuredDevice = deviceConfigurationService.configureDevice(id);

        return ApiResponse.ok(configuredDevice);
    }
}
