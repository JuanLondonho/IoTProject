package com.udea.iotProject.repository;

import com.udea.iotProject.model.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<DeviceStatus, Integer> {

    @Override
    <S extends DeviceStatus> S save(S s);

    @Query(value = "SELECT * FROM devices", nativeQuery = true)
    List<DeviceStatus> deviceStatus();
}
