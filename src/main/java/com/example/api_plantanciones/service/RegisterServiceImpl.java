package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.repository.RegisterRepository;
import com.example.api_plantanciones.repository.SensorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {

//    Inyectamos el repositorio
    private final RegisterRepository repository;
    private final SensorRepository sensorRepository;

//    Creamos el constructor
    public RegisterServiceImpl(RegisterRepository repository, SensorRepository sensorRepository) {
        this.repository = repository;
        this.sensorRepository = sensorRepository;
    }


    @Override
    public List<Register> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Register save(Register register, Long id) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(id);

        if(optionalSensor.isPresent()){
            register.setSensor(optionalSensor.get());

            return repository.save(register);
        }else {
            // Maneja el caso en que el sensor no se encuentre en la base de datos
            throw new IllegalArgumentException("Sensor not found with id: " + id);
        }
    }

    @Override
    public Register update(Long id, Register register) {
        Optional<Register> optionalRegister = this.repository.findById(id);

        if(optionalRegister.isPresent()) {
            Register updatedRegister = optionalRegister.get();
            updatedRegister.setHumidity(register.getHumidity());
            updatedRegister.setTemperature(register.getTemperature());
            updatedRegister.setDateOfDataRegistration(register.getDateOfDataRegistration());
            updatedRegister.setTimeOfDataRegistration(register.getTimeOfDataRegistration());

            return this.repository.save(updatedRegister);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Register> getAllRegistersByPlantationId(Long plantationId) {
        // Obtenenemos una lista de sensores para un id de plantación
        List<Sensor> sensors = sensorRepository.findAllByPlantationId(plantationId);
        // Obtenenemos los ids de los sensores de dicha plantación
        List<Long> sensorIds = sensors.stream().map(Sensor::getId).collect(Collectors.toList());
        // Llamamos al método creado en el repositorio de register
        return repository.findAllBySensorIdIn(sensorIds);
    }
}
