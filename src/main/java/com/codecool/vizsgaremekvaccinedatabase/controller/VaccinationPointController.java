package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccinationPointService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccinationpoint")
public class VaccinationPointController {

    VaccinationPointService service;

    public VaccinationPointController(VaccinationPointService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VaccinationPoint> save(@Valid @RequestBody VaccinationPoint s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.save(s));
    }

    @PutMapping
    public ResponseEntity<VaccinationPoint> update(@Valid @RequestBody VaccinationPoint s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.update(s));
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
