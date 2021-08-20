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
    public void whenGetFindAllShouldReturnListOfVaccinesTest() throws Exception {
        List<Vaccine> vaccines = List.of(
                new Vaccine(),
                new Vaccine()
        );
        when(vaccineService.findAll()).thenReturn(vaccines);
        this.mockMvc.perform(get("/vaccine"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(vaccines.get(0).getUuid()))
                .andExpect(jsonPath("$[1].uuid").value(vaccines.get(1).getUuid()));
    }

    @Test
    public void whenPostSaveVaccineReturnVaccine() throws Exception {
        Vaccine vaccine = new Vaccine();
        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(new Vaccine()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.uuid").value(vaccine.getUuid()));
    }

    @Test
    public void whenPut_saveVaccine_ReturnVaccine() throws Exception {
        Vaccine vaccine = new Vaccine();

        when(vaccineService.save(any(Vaccine.class))).thenReturn(vaccine);


        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccine")
                .content(objectMapper.writeValueAsString(new Vaccine()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.uuid").value(vaccine.getUuid()));

        Vaccine vaccineUpdated = new Vaccine();
        vaccineUpdated.setId(1L);
        vaccineUpdated.setUuid("3abc");
        when(vaccineService.update(any(Vaccine.class))).thenReturn(vaccineUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/vaccine")
                .content(objectMapper.writeValueAsString(vaccineUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(vaccineUpdated.getUuid()));
    }

    @Test
    public void whenGetFindByIdReturnVaccine() throws Exception {
        Vaccine vaccine1 = new Vaccine();
        Vaccine vaccine2 = new Vaccine();
        when(vaccineService.findById(1L)).thenReturn(java.util.Optional.of(vaccine1));
        when(vaccineService.findById(2L)).thenReturn(java.util.Optional.of(vaccine2));
        this.mockMvc.perform(get("/vaccine/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(vaccine1.getUuid()));
        this.mockMvc.perform(get("/vaccine/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(vaccine2.getUuid()));
    }

    @Test
    public void whenDeleteByIdStatusIsOk() throws Exception {
        this.mockMvc.perform(delete("/vaccine/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
