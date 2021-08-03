package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccinationPointService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccinationpoints")
public class VaccinationPointController {

    VaccinationPointService service;

    public VaccinationPointController(VaccinationPointService service) {
        this.service = service;
    }

    @PostMapping
    public VaccinationPoint save(@RequestBody VaccinationPoint s) {
        return service.save(s);
    }

    @GetMapping
    public List<VaccinationPoint> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<VaccinationPoint> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
