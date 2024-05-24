package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Plantation;
import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.repository.PlantationRepository;
import com.example.api_plantanciones.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

//    Inyectamos el repositorio
    private final SensorRepository repository;
//    También hemos inyectado el repositorio de Plantation porque se usará en el endpoint /api/sensors/new
    private final PlantationRepository plantationRepository;

    public SensorServiceImpl(SensorRepository repository, PlantationRepository plantationRepository) {
        this.repository = repository;
        this.plantationRepository = plantationRepository;
    }

    @Override
    public List<Sensor> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Sensor save(Sensor sensor, Long id) {

        Optional<Plantation> optionalPlantation = this.plantationRepository.findById(id);

        if (optionalPlantation.isPresent()) {
            Plantation plantation = optionalPlantation.get();

            // Asigna la Plantation al Sensor
            sensor.setPlantation(plantation);

            // Guarda el Sensor en la base de datos
            return repository.save(sensor);
        } else {
            // Maneja el caso en que la Plantation no se encuentra en la base de datos
            throw new IllegalArgumentException("Plantation not found with id: " + id);
        }
    }

    @Override
    public Sensor update(Long id, Sensor sensor) {
        Optional<Sensor> optionalSensor = this.repository.findById(id);

//        Solo se actualizará la fecha de instalación y el lugar de instalación
        if(optionalSensor.isPresent()) {
            Sensor updatedSensor = optionalSensor.get();
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

    @Override
    public String tempYHumeMediaPorFecha(Long sensorId, Date initialDate, Date finalDate) {
        Optional<Sensor> optionalSensor = this.repository.findById(sensorId);

        if(optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();

        }
        return "";
    }
}
