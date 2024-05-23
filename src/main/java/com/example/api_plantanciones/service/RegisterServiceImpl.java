package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.repository.RegisterRepository;
import com.example.api_plantanciones.repository.SensorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
