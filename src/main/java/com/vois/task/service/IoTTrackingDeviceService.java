package com.vois.task.service;

import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.enumuration.Status;
import com.vois.task.mapper.IoTTrackingDeviceMapper;
import com.vois.task.model.IoTTrackingDevice;
import com.vois.task.repository.IoTTrackingDeviceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IoTTrackingDeviceService {

    private IoTTrackingDeviceRepository ioTTrackingDeviceRepository;

    private IoTTrackingDeviceMapper mapper;

    public IoTTrackingDeviceService(IoTTrackingDeviceRepository ioTTrackingDeviceRepository,
                                    IoTTrackingDeviceMapper ioTTrackingDeviceMapper) {

        this.ioTTrackingDeviceRepository = ioTTrackingDeviceRepository;
        this.mapper = ioTTrackingDeviceMapper;
    }

    public List<IoTTrackingDeviceDTO> getActiveDevices(int offset, int size) {

        List<IoTTrackingDevice> devices = ioTTrackingDeviceRepository.
                findByStatusIsOrderByPin(Status.ACTIVE, PageRequest.of(offset,size));

        return mapper.toDTOList(devices);
    }

    public IoTTrackingDeviceDTO addDevice(String pin) {

        IoTTrackingDevice trackingDevice = new IoTTrackingDevice();

        trackingDevice.setPin(pin);

        ioTTrackingDeviceRepository.save(trackingDevice);

        return mapper.toDTO(trackingDevice);
    }

    public IoTTrackingDeviceDTO updateDevice(IoTTrackingDeviceDTO ioTTrackingDeviceDTO) {

        IoTTrackingDevice trackingDevice = mapper.toEntity(ioTTrackingDeviceDTO);

        ioTTrackingDeviceRepository.save(trackingDevice);

        return mapper.toDTO(trackingDevice);
    }

    public void deleteDevice(String id) {

        ioTTrackingDeviceRepository.deleteById(id);
    }
}
