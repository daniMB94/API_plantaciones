package com.example.api_plantanciones.service;


import com.example.api_plantanciones.dto.plantation.PlantationAvgHumTemByDate;
import com.example.api_plantanciones.model.Register;

import java.util.Date;
import java.util.List;

public interface RegisterService {
//    CRUD

    List<Register> findAll();
    Register save(Register register, Long id);
    Register update(Long id, Register register);
    void deleteById(Long id);
//  Other queries
    List<Register> getAllRegistersByPlantationId(Long plantationId);
    List<Register> allRegistersByDate(List<Register> registers, Date date);
    PlantationAvgHumTemByDate plantationAvgHumTempByDate(Long plantationId, Date date);

}
