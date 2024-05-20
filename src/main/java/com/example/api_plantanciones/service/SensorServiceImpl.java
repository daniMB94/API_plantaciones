package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

//    Inyectamos el repositorio
    private final SensorRepository repository;

    public SensorServiceImpl(SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sensor> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Sensor save(Sensor sensor) {
        return this.repository.save(sensor);
    }

    @Override
    public Sensor update(Long id, Sensor sensor) {
        Optional<Sensor> optionalSensor = this.repository.findById(id);

        if(optionalSensor.isPresent()) {
            Sensor updatedSensor = optionalSensor.get();
            updatedSensor.setPlantation(sensor.getPlantation());
            updatedSensor.setRegisters(sensor.getRegisters());
            updatedSensor.setInstallationDate(sensor.getInstallationDate());
            updatedSensor.setInstallationPlace(sensor.getInstallationPlace());

            return this.repository.save(updatedSensor);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
