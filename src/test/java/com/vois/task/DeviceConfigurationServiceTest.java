package com.vois.task;

import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.enumuration.Status;
import com.vois.task.mapper.IoTTrackingDeviceMapper;
import com.vois.task.model.IoTTrackingDevice;
import com.vois.task.repository.IoTTrackingDeviceRepository;
import com.vois.task.service.DeviceConfigurationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceConfigurationServiceTest {

    @Mock
    private IoTTrackingDeviceRepository ioTTrackingDeviceRepository;

    private IoTTrackingDeviceMapper mapper = Mappers.getMapper(IoTTrackingDeviceMapper.class);

    @Test
    public void testConfigureDevice() {

        when(ioTTrackingDeviceRepository.findById(anyString()))
                .thenReturn(Optional.of(getTrackingDevice()));

        DeviceConfigurationService configurationService = new DeviceConfigurationService(ioTTrackingDeviceRepository, mapper);

        IoTTrackingDeviceDTO configuredDevice = configurationService.configureDevice("1234");

        assertTrue(configuredDevice.getStatus().equals(Status.ACTIVE));
        assertTrue(configuredDevice.getTemperature() >= 0 && configuredDevice.getTemperature() <= 10);

    }

    private IoTTrackingDevice getTrackingDevice() {

        IoTTrackingDevice device = new IoTTrackingDevice();

        device.setStatus(Status.READY);
        device.setId("1234");
        device.setTemperature(1);

        return device;
    }

}
