package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccinedata")
public class VaccineDataController {

    VaccineDataService service;

    public VaccineDataController(VaccineDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VaccineData> save(@Valid @RequestBody VaccineData s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.save(s));
    }

    @PutMapping
    public ResponseEntity<VaccineData> update(@Valid @RequestBody VaccineData s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.update(s));
    }

    @GetMapping
    public List<VaccineData> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{vaccineName}")
    public Optional<VaccineData> findByName(@PathVariable String vaccineName) {
        return service.findByName(vaccineName);
    }

    @DeleteMapping(value = "/{vaccineName}")
    public void deleteById(@PathVariable String vaccineName) {
        service.deleteByName(vaccineName);
    }
}
