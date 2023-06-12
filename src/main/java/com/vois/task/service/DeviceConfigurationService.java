package com.vois.task.service;

import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.enumuration.Status;
import com.vois.task.mapper.IoTTrackingDeviceMapper;
import com.vois.task.model.IoTTrackingDevice;
import com.vois.task.repository.IoTTrackingDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DeviceConfigurationService {

    private final IoTTrackingDeviceRepository ioTTrackingDeviceRepository;

    private final IoTTrackingDeviceMapper mapper;

    private final Integer MAX_TEMP = 10;

    private final Integer MIN_TEMP = 0;

    public DeviceConfigurationService(IoTTrackingDeviceRepository ioTTrackingDeviceRepository, IoTTrackingDeviceMapper mapper) {

        this.ioTTrackingDeviceRepository = ioTTrackingDeviceRepository;
        this.mapper = mapper;
    }

    public IoTTrackingDeviceDTO configureDevice(String id) {

        IoTTrackingDevice trackingDevice = ioTTrackingDeviceRepository.findById(id).get();

        trackingDevice.setStatus(Status.ACTIVE);
        Random random = new Random();
        Integer randomTemp = random.nextInt(MAX_TEMP + 1);
        trackingDevice.setTemperature(randomTemp);

        ioTTrackingDeviceRepository.save(trackingDevice);

        return mapper.toDTO(trackingDevice);
    }
}
