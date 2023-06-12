package com.vois.task.integration;

import com.vois.task.VOISTaskApplication;
import com.vois.task.repository.IoTTrackingDeviceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@Testcontainers
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes={ VOISTaskApplication.class })
public class IntegrationTestBase extends ContainerBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IoTTrackingDeviceRepository ioTTrackingDeviceRepository;

    protected MockMvc mockMvc;

    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {

        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void clearDb() {
       ioTTrackingDeviceRepository.deleteAll();
    }
}
