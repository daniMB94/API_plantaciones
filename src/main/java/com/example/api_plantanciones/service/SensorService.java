package com.example.api_plantanciones.service;

import com.example.api_plantanciones.dto.sensor.SensorHumTemAvgHist;
import com.example.api_plantanciones.model.Sensor;

import java.util.Date;
import java.util.List;

public interface SensorService {

//    CRUD
    List<Sensor> findAll();
    Sensor save(Sensor sensor, Long id);
    Sensor update(Long id, Sensor sensor);
    void deleteById(Long id);

    //    Consultas específicas
    String tempYHumeMediaPorFecha(Long sensorId, Date initialDate, Date finalDate);
    SensorHumTemAvgHist temYHumeMediaHistorica (Long sensorId);
}
