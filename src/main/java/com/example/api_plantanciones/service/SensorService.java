package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Sensor;

import java.util.List;

public interface SensorService {

//    CRUD
    List<Sensor> findAll();
    Sensor save(Sensor sensor);
    Sensor update(Long id, Sensor sensor);
    void deleteById(Long id);
}
