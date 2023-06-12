package com.vois.task.controller;

import com.vois.task.dto.ApiResponse;
import com.vois.task.dto.IoTCreateDeviceDTO;
import com.vois.task.dto.IoTTrackingDeviceDTO;
import com.vois.task.service.IoTTrackingDeviceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/iot/devices")
public class IoTTrackingDeviceController {

    private final IoTTrackingDeviceService ioTTrackingDeviceService;

    public IoTTrackingDeviceController(IoTTrackingDeviceService ioTTrackingDeviceService) {

        this.ioTTrackingDeviceService = ioTTrackingDeviceService;
    }

    @GetMapping
    public ApiResponse getActiveDevices(@RequestParam Integer offset, @RequestParam Integer size) {

        List<IoTTrackingDeviceDTO> deviceDTOList = ioTTrackingDeviceService.getActiveDevices(offset,size);

        return ApiResponse.ok(deviceDTOList);
    }

    @PostMapping
    public ApiResponse addDevice(@RequestBody @Valid IoTCreateDeviceDTO createDeviceDTO) {

        IoTTrackingDeviceDTO ioTCreateDeviceDTO = ioTTrackingDeviceService.addDevice(createDeviceDTO.getPin());

        return ApiResponse.ok(ioTCreateDeviceDTO);
    }

    @PutMapping
    public ApiResponse updateDevice(@RequestBody IoTTrackingDeviceDTO ioTTrackingDeviceDTO) {

        ioTTrackingDeviceDTO = ioTTrackingDeviceService.updateDevice(ioTTrackingDeviceDTO);

        return ApiResponse.ok(ioTTrackingDeviceDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteDevice(@PathVariable String id) {

        ioTTrackingDeviceService.deleteDevice(id);

        return ApiResponse.ok();
    }
}
