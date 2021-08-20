package com.codecool.vizsgaremekvaccinedatabase.unit;

import com.codecool.vizsgaremekvaccinedatabase.controller.VaccineController;
import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VaccineController.class)
public class VaccineUnitTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private VaccineService vaccineService;

    @Test
    public void whenGet_findAll_returnListOfVaccines() throws Exception {
        List<Vaccine> vaccines = List.of(
                new Vaccine("Pfizer-BioNtech", 2, 18),
                new Vaccine("Moderna", 2, 18)
        );
        when(vaccineService.findAll()).thenReturn(vaccines);
        this.mockMvc.perform(get("/vaccine"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(vaccines.get(0).getName()))
                .andExpect(jsonPath("$[0].dosesNeeded").value(vaccines.get(0).getDosesNeeded()))
                .andExpect(jsonPath("$[0].minAge").value(vaccines.get(0).getMinAge()))

                .andExpect(jsonPath("$[1].name").value(vaccines.get(1).getName()))
                .andExpect(jsonPath("$[1].dosesNeeded").value(vaccines.get(1).getDosesNeeded()))
                .andExpect(jsonPath("$[1].minAge").value(vaccines.get(1).getMinAge()));
    }

    @Test
    public void whenPost_newVaccine_returnSavedVaccine() throws Exception {
        Vaccine vaccine = new Vaccine("Moderna", 2, 18);

        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(vaccine))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccine.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccine.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccine.getMinAge()));
    }

    @Test
    public void whenPut_existingVaccineWithNewData_updateAndReturnVaccine() throws Exception {
        Vaccine vaccine = new Vaccine("Moderna", 2, 18);

        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(vaccine))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccine.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccine.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccine.getMinAge()));

        Vaccine vaccineUpdated = new Vaccine("Moderna", 3, 18);
        vaccineUpdated.setId(1L);
        when(vaccineService.update(any(Vaccine.class))).thenReturn(vaccineUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/vaccine")
                .content(objectMapper.writeValueAsString(vaccineUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineUpdated.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineUpdated.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineUpdated.getMinAge()));
    }

    @Test
    public void whenGet_findById_returnCorrectVaccine() throws Exception {
        Vaccine vaccine1 = new Vaccine("Pfizer-BioNtech", 2, 18);
        Vaccine vaccine2 = new Vaccine("Moderna", 2, 18);

        when(vaccineService.findById(1L)).thenReturn(java.util.Optional.of(vaccine1));
        when(vaccineService.findById(2L)).thenReturn(java.util.Optional.of(vaccine2));

        this.mockMvc.perform(get("/vaccine/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccine1.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccine1.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccine1.getMinAge()));
        this.mockMvc.perform(get("/vaccine/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccine2.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccine2.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccine2.getMinAge()));
    }

    @Test
    public void whenDeleteByIdStatusIsOk() throws Exception {
        this.mockMvc.perform(delete("/vaccine/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenPost_newVaccineWithInvalidData_StatusBadRequest() throws Exception {
        Vaccine vaccine = new Vaccine("Moderna", 0, 18);

        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(vaccine))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenPut_withInvalidFieldData_StatusBadRequest() throws Exception {
        Vaccine vaccine = new Vaccine("Moderna", 2, 18);

        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(vaccine))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccine.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccine.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccine.getMinAge()));

        Vaccine vaccineUpdated = new Vaccine("Moderna", 0, 18);
        vaccineUpdated.setId(1L);
        when(vaccineService.update(any(Vaccine.class))).thenReturn(vaccineUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/vaccine")
                .content(objectMapper.writeValueAsString(vaccineUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
