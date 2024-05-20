package com.example.api_plantanciones.controller;


import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

//    Inyectamos el servicio
    private SensorService sensorService;

    public SensorController(SensorService service) {
        this.sensorService = service;
    }

//    END POINTS DE LOS SENSORES
    @GetMapping("/all")
    public ResponseEntity<List<Sensor>> findAll() {
        List<Sensor> sensors = sensorService.findAll();
        if(sensors.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sensors);
    }
}
