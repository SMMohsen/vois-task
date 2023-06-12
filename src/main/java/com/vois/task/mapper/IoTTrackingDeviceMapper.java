package com.vois.task.mapper;

import com.vois.task.model.IoTTrackingDevice;
import com.vois.task.dto.IoTTrackingDeviceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IoTTrackingDeviceMapper {

    IoTTrackingDeviceDTO toDTO(IoTTrackingDevice ioTTrackingDevice);

    IoTTrackingDevice toEntity(IoTTrackingDeviceDTO ioTTrackingDeviceDTO);

    List<IoTTrackingDeviceDTO> toDTOList(List<IoTTrackingDevice> ioTTrackingDevices);
}
