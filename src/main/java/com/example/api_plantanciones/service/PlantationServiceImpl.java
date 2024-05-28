package com.example.api_plantanciones.service;

import com.example.api_plantanciones.model.Plantation;
import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.repository.PlantationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlantationServiceImpl implements PlantationService {

//    Inyectamos el repositorio
    private final PlantationRepository repository;

//    Es más recomendable usar un constructor para inyectar el repositorio para facilitar el testing


    public PlantationServiceImpl(PlantationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Plantation> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Plantation> findByName(String name) {
        Objects.requireNonNull(name);
        return this.repository.findByName(name);
    }

    @Override
    public Plantation save(Plantation plantation) {
        return this.repository.save(plantation);
    }

    @Override
    public Plantation update(Long id, Plantation plantation) {
        Optional<Plantation> optionalPlantation = this.repository.findById(id);

        if(optionalPlantation.isPresent()) {
            Plantation updatedPlantation = optionalPlantation.get();
            updatedPlantation.setName(plantation.getName());
            updatedPlantation.setTypeOfCrop(plantation.getTypeOfCrop());
            updatedPlantation.setLatitude(plantation.getLatitude());
            updatedPlantation.setLongitude(plantation.getLongitude());

            return this.repository.save(updatedPlantation);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

}
