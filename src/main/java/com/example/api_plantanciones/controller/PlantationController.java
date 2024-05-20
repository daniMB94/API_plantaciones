package com.example.api_plantanciones.controller;

import com.example.api_plantanciones.model.Plantation;
import com.example.api_plantanciones.service.PlantationService;
import com.example.api_plantanciones.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/plantations")
public class PlantationController {

//    Inyectamos los servicios
    private PlantationService plantationService;

//    Inyectamos con el constructor en vez de @Autowired
    public PlantationController(PlantationService service) {
        this.plantationService = service;
    }
//  END POINTS DE LAS PLANTACIONES
    @GetMapping("/all")
    public ResponseEntity<List<Plantation>> findAll() {
        List<Plantation> plantations = plantationService.findAll();
        if(plantations.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(plantations);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Plantation> findByName(@PathVariable String name) {
        return this.plantationService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Plantation> save(@RequestBody Plantation plantation){
//        Comprobaciones de seguridad
//        ...
        this.plantationService.save(plantation);
        return ResponseEntity.ok(plantation);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Plantation> update(@PathVariable Long id, @RequestBody Plantation plantation){
        Plantation updatedPlantation = this.plantationService.update(id, plantation);
        return ResponseEntity.ok(updatedPlantation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Plantation> deleteById(@PathVariable Long id) {
        this.plantationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Plantation> deleteAll() {
        this.plantationService.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
