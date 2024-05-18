package com.example.api_plantanciones.controller;

import com.example.api_plantanciones.model.Plantation;
import com.example.api_plantanciones.service.PlantationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PlantationController {

//    Inyectamos el servicio
    private PlantationService service;

//    Inyectamos con el constructor en vez de @Autowired
    public PlantationController(PlantationService service) {
        this.service = service;
    }

    @GetMapping("/plantations")
    public ResponseEntity<List<Plantation>> findAll() {
        List<Plantation> plantations = service.findAll();
        if(plantations.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(plantations);
    }

    @GetMapping("/plantations/name/{name}")
    public ResponseEntity<Plantation> findByName(@PathVariable String name) {
        return this.service.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/plantation/new")
    public ResponseEntity<Plantation> save(@RequestBody Plantation plantation){
//        Comprobaciones de seguridad
//        ...
        this.service.save(plantation);
        return ResponseEntity.ok(plantation);
    }

    @PutMapping("/plantation/update/id/{id}")
    public ResponseEntity<Plantation> update(@PathVariable Long id, @RequestBody Plantation plantation){
        Plantation updatedPlantation = this.service.update(id, plantation);
        return ResponseEntity.ok(updatedPlantation);
    }

    @DeleteMapping("/plantations/delete/{id}")
    public ResponseEntity<Plantation> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/plantations/delete/all")
    public ResponseEntity<Plantation> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
