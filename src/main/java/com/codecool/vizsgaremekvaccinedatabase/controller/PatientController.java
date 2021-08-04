package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import com.codecool.vizsgaremekvaccinedatabase.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public Patient save(@RequestBody Patient s) { return service.save(s); }

    @PutMapping
    public Patient update(@RequestBody Patient s) {
        return service.update(s);
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
