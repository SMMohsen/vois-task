package com.vois.task;

import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.enumuration.Status;
import com.vois.task.mapper.IoTTrackingDeviceMapper;
import com.vois.task.repository.IoTTrackingDeviceRepository;
import com.vois.task.service.IoTTrackingDeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class IoTTrackingDeviceServiceTest {

    @Mock
    private IoTTrackingDeviceRepository ioTTrackingDeviceRepository;

    private IoTTrackingDeviceMapper mapper = Mappers.getMapper(IoTTrackingDeviceMapper.class);


    @Test
    public void addDeviceTest() {


        IoTTrackingDeviceService service = new IoTTrackingDeviceService(ioTTrackingDeviceRepository, mapper);

        IoTTrackingDeviceDTO createdDevice = service.addDevice("1234567");

        assertTrue(createdDevice.getStatus().equals(Status.READY));
        assertTrue(createdDevice.getTemperature().equals(1));
        assertTrue(createdDevice.getPin().equals("1234567"));

    }

    @Test
    public void updateDeviceTest() {

        IoTTrackingDeviceService service = new IoTTrackingDeviceService(ioTTrackingDeviceRepository, mapper);

        IoTTrackingDeviceDTO updatedDevice = service.updateDevice(getDevice());

        assertTrue(updatedDevice.getTemperature().equals(3));
        assertTrue(updatedDevice.getStatus().equals(Status.ACTIVE));
    }

    private IoTTrackingDeviceDTO getDevice() {

        IoTTrackingDeviceDTO deviceDTO = new IoTTrackingDeviceDTO();

        deviceDTO.setTemperature(3);
        deviceDTO.setStatus(Status.ACTIVE);

        return deviceDTO;
    }
 }
