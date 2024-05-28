package com.example.api_plantanciones.repository;

import com.example.api_plantanciones.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllByPlantationId(Long plantationId);
}
