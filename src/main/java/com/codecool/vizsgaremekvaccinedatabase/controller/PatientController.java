package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import com.codecool.vizsgaremekvaccinedatabase.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
@RequestMapping(value = "/patient")
public class PatientController {

    PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Patient> save(@Valid @RequestBody Patient s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.save(s));
    }

    @PutMapping
    public ResponseEntity<Patient> update(@Valid @RequestBody Patient s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.update(s));
    }

    @GetMapping
    public List<Patient> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Patient> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
