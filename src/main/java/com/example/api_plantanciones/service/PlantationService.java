package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Plantation;

import java.util.List;
import java.util.Optional;

public interface PlantationService {

    // Retrieve
    List<Plantation> findAll();
    Optional<Plantation> findByName(String name);

    // Create
    Plantation save(Plantation plantation);

    // Update
    Plantation update(Long id, Plantation plantation);

    // Delete
    void deleteById(Long id);
    void deleteAll();



}
