package com.vois.task.integration;

import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startables;

public abstract class ContainerBase {

    static final GenericContainer mongo;

    static {
        mongo = new GenericContainer("mongo")
                .withExposedPorts(27017)
                .withReuse(true);
    }

    static {
        Startables.deepStart(mongo).join();
    }

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongo::getHost);
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "test");
    }
}
