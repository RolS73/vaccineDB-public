package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccines")
public class VaccineController {

    VaccineService service;

    public VaccineController(VaccineService service) {
        this.service = service;
    }

    @PostMapping
    public VaccineData save(VaccineData s) {
        return service.save(s);
    }

    @GetMapping
    public List<VaccineData> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<VaccineData> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
