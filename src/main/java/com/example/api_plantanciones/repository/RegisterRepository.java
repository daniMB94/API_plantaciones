package com.example.api_plantanciones.repository;

import com.example.api_plantanciones.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findAllBySensorIdIn (List<Long> sensorIds);

    List<Register> findAllByDateOfDataRegistration(Date date);
}
