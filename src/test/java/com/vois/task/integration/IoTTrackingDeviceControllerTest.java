package com.vois.task.integration;

import com.vois.task.dto.IoTCreateDeviceDTO;
import com.vois.task.dto.IoTTrackingDeviceDTO;

import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class IoTTrackingDeviceControllerTest extends IntegrationTestBase {

    @Test
    public void getConfiguredDevicesTest() throws Exception {

        List<String> devices = new ArrayList<>();

        devices.add(addNewDevice("2233445"));
        devices.add(addNewDevice("1122334"));
        devices.add(addNewDevice("3344556"));

        devices.stream()
                .forEach(id -> {
                    try {
                        mockMvc.perform(post("/iot/configuration/"+id));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        mockMvc.perform(get("/iot/devices?offset=0&size=3"))
                .andExpect(jsonPath("$.data[0].pin").value("1122334"))
                .andExpect(jsonPath("$.data[1].pin").value("2233445"))
                .andExpect(jsonPath("$.data[2].pin").value("3344556"));
    }

    @Test
    public void getConfiguredDevicesWithNoConfiguredTest() throws Exception {

        List<String> devices = new ArrayList<>();

        devices.add(addNewDevice("2233445"));
        devices.add(addNewDevice("1122334"));
        devices.add(addNewDevice("3344556"));

        mockMvc.perform(get("/iot/devices?offset=0&size=3"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void addDeviceTest() throws Exception {

        IoTCreateDeviceDTO createDeviceDTO = new IoTCreateDeviceDTO();
        createDeviceDTO.setPin("1122334");

        mockMvc.perform(post("/iot/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDeviceDTO)))
                .andExpect(jsonPath("$.data.pin").value("1122334"))
                .andExpect(jsonPath("$.data.status").value("READY"));

    }

    @Test
    public void addDeviceWithWrongPinLengthTest() throws Exception {

        IoTCreateDeviceDTO createDeviceDTO = new IoTCreateDeviceDTO();
        createDeviceDTO.setPin("112233");

        mockMvc.perform(post("/iot/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDeviceDTO)))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.success").value(false));


    }

    @Test
    public void updateDeviceTest() throws Exception {

        String deviceId = addNewDevice("1234567");

        IoTTrackingDeviceDTO deviceToUpdate = new IoTTrackingDeviceDTO();
        deviceToUpdate.setId(deviceId);
        deviceToUpdate.setPin("22334455");


        mockMvc.perform(put("/iot/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(deviceToUpdate)))

                .andExpect(jsonPath("$.data.pin").value("22334455"))
                .andExpect(jsonPath("$.data.id").value(deviceId));
    }

    @Test
    public void deleteDeviceTest() throws Exception {

        String deviceId = addNewDevice("1234567");

        mockMvc.perform(delete("/iot/devices/"+deviceId))
                .andExpect(status().is2xxSuccessful());

    }


    private String addNewDevice(String pin) throws Exception {

        IoTCreateDeviceDTO createDeviceDTO = new IoTCreateDeviceDTO();
        createDeviceDTO.setPin(pin);

        MvcResult result = mockMvc.perform(post("/iot/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createDeviceDTO))).andReturn();

      return  mapper.readTree(result.getResponse().getContentAsByteArray()).get("data").get("id").asText();
    }
}
