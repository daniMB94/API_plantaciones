package com.example.api_plantanciones.repository;

import com.example.api_plantanciones.model.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {

    Optional<Plantation> findByName(String name);
}
