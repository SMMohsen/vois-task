package com.vois.task.repository;

import com.vois.task.model.IoTTrackingDevice;
import com.vois.task.enumuration.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IoTTrackingDeviceRepository extends MongoRepository<IoTTrackingDevice, String> {

    List<IoTTrackingDevice> findByStatusIsOrderByPin(Status status, Pageable pageable);
}
