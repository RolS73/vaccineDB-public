package com.codecool.vizsgaremekvaccinedatabase.controller;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vaccine_data")
public class VaccineDataController {

    VaccineDataService service;

    public VaccineDataController(VaccineDataService service) {
        this.service = service;
    }

    @PostMapping
    public VaccineData save(@RequestBody VaccineData s) {
        return service.save(s);
    }

    @PutMapping
    public VaccineData update(@RequestBody VaccineData s) {
        return service.update(s);
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
