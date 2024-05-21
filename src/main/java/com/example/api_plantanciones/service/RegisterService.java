package com.example.api_plantanciones.service;


import com.example.api_plantanciones.model.Register;

import java.util.List;

public interface RegisterService {
//    CRUD

    List<Register> findAll();
    Register save(Register register, Long id);
    Register update(Long id, Register register);
    void deleteById(Long id);
}
