package com.example.api_plantanciones.controller;


import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
/*
    @PutMapping("/update/id/{id]")
    public ResponseEntity<Register> update(@PathVariable Long id,)

 */
}
