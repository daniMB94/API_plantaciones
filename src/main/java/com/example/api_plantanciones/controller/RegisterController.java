package com.example.api_plantanciones.controller;


import com.example.api_plantanciones.dto.plantation.PlantationAvgHumTemByDate;
import com.example.api_plantanciones.dto.register.RegisterAllByPlantation;
import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.service.RegisterService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/registers")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService service) {
        this.registerService = service;
    }

//    END POINTS DE LOS REGISTROS
    @GetMapping("/all")
    public ResponseEntity<List<Register>> getAll() {
        List<Register> registers = registerService.findAll();
        if(registers.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(registers);
    }

    @PostMapping("/new/{associatedSensorId}")
    public ResponseEntity<Register> save(@RequestBody Register register ,@PathVariable Long associatedSensorId) {
        Register savedRegister = registerService.save(register, associatedSensorId);
        return ResponseEntity.ok(savedRegister);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Register> update(@PathVariable Long id, @RequestBody Register register) {
        Register updatedRegister = this.registerService.update(id, register);
        return ResponseEntity.ok(updatedRegister);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Register> delete(@PathVariable Long id) {
        this.registerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/byPlantationId/{id}")
    public ResponseEntity<RegisterAllByPlantation> getAllRegistersByPlantationId(@PathVariable Long id) {
        List<Register> registers = this.registerService.getAllRegistersByPlantationId(id);
        return ResponseEntity.ok(new RegisterAllByPlantation(registers));
    }

    @GetMapping("/all/byPlantationId/{id}/date/{date}")
    public ResponseEntity<RegisterAllByPlantation> getAllRegistersByPlantationId(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Register> registers = this.registerService.getAllRegistersByPlantationId(id);
        List<Register> registersByDate = this.registerService.allRegistersByDate(registers, date);
        return ResponseEntity.ok(new RegisterAllByPlantation(registersByDate));
    }

    @GetMapping("/average/humidityAndTemperature/byPlantationId/{id}/date/{date}")
    public ResponseEntity<PlantationAvgHumTemByDate> plantationAvgHumTemByDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(this.registerService.plantationAvgHumTempByDate(id, date));
    }

    @GetMapping("/all/bySensorId/{id}")
    public ResponseEntity<List<Register>> allRegistersBySensorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.registerService.findAllRegisterBySensorId(id));
    }

    @GetMapping("/all/bySensorId/{id}/date/{date}")
    public ResponseEntity<List<Register>> allRegisterBySensorIdAndDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(this.registerService.findAllRegisterBySensorIdAndDate(id, date));
    }

}
