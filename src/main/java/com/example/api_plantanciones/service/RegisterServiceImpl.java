package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.repository.RegisterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

//    Inyectamos el repositorio
    private final RegisterRepository repository;

//    Creamos el constructor
    public RegisterServiceImpl(RegisterRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Register> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Register save(Register register, Long id) {
        return null;
    }

    @Override
    public Register update(Long id, Register register) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
