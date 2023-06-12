package com.vois.task.integration;


import com.vois.task.dto.IoTCreateDeviceDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DeviceConfigurationControllerTest extends IntegrationTestBase {

    @Test
    public void configureDeviceTest() throws Exception {

        IoTCreateDeviceDTO createDeviceDTO = new IoTCreateDeviceDTO();
        createDeviceDTO.setPin("1122334");

        MvcResult result = mockMvc.perform(post("/iot/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createDeviceDTO))).andReturn();

        String deviceId = mapper.readTree(result.getResponse().getContentAsByteArray()).get("data").get("id").asText();

        mockMvc.perform(post("/iot/configuration/"+deviceId))
                .andExpect(jsonPath("$.data.status").value("ACTIVE"))
                .andExpect(jsonPath("$.data.id").value(deviceId));

    }

    @Test
    public void configureDeviceWithWrongIdTest() throws Exception {


        String deviceId = UUID.randomUUID().toString();

        mockMvc.perform(post("/iot/configuration/"+deviceId))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));

    }
}
