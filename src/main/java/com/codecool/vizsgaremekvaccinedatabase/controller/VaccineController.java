package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccine")
public class VaccineController {

    VaccineService service;

    public VaccineController(VaccineService service) {
        this.service = service;
    }

    @PostMapping
    public Vaccine save(@RequestBody Vaccine s) {
        return service.save(s);
    }

    @PutMapping
    public Vaccine update(@RequestBody Vaccine s) {
        return service.update(s);
    }

    @GetMapping
    public List<Vaccine> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Vaccine> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
