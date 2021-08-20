package com.codecool.vizsgaremekvaccinedatabase.unit;

import com.codecool.vizsgaremekvaccinedatabase.controller.VaccinationPointController;
import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccinationPointService;
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

@WebMvcTest(VaccinationPointController.class)
public class VaccinationPointUnitTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private VaccinationPointService vaccinationPointService;

    @Test
    public void whenGetFindAllShouldReturnListOfVaccinationPointsTest() throws Exception {
        List<VaccinationPoint> vaccinationPoints = List.of(
                new VaccinationPoint(
                        "Szent Borbála Kórház",
                        "Komárom-Esztergom Megye",
                        "2800 Tatabánya",
                        "Dózsa Gy. út 77., \"H\" épület földszint"),

                new VaccinationPoint(
                        "Szent György Egyetemi Oktató Kórház",
                        "Fejér Megye",
                        "8000 Székesfehérvár",
                        "Seregélyesi út 3. Rendelőintézet II. szint")
        );
        when(vaccinationPointService.findAll()).thenReturn(vaccinationPoints);
        this.mockMvc.perform(get("/vaccinationpoint"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].name").value(vaccinationPoints.get(0).getName()))
                .andExpect(jsonPath("$[0].region").value(vaccinationPoints.get(0).getRegion()))
                .andExpect(jsonPath("$[0].city").value(vaccinationPoints.get(0).getCity()))
                .andExpect(jsonPath("$[0].address").value(vaccinationPoints.get(0).getAddress()))

                .andExpect(jsonPath("$[1].name").value(vaccinationPoints.get(1).getName()))
                .andExpect(jsonPath("$[1].region").value(vaccinationPoints.get(1).getRegion()))
                .andExpect(jsonPath("$[1].city").value(vaccinationPoints.get(1).getCity()))
                .andExpect(jsonPath("$[1].address").value(vaccinationPoints.get(1).getAddress()));
    }

    @Test
    public void whenPostSaveVaccinationPointReturnVaccinationPoint() throws Exception {
        VaccinationPoint vaccinationPoint = new VaccinationPoint(
                "Szent Borbála Kórház",
                "Komárom-Esztergom Megye",
                "2800 Tatabánya",
                "Dózsa Gy. út 77., \"H\" épület földszint");

        when(vaccinationPointService.save(any(VaccinationPoint.class))).thenReturn(vaccinationPoint);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccinationpoint")
                .content(objectMapper.writeValueAsString(vaccinationPoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccinationPoint.getName()))
                .andExpect(jsonPath("$.region").value(vaccinationPoint.getRegion()))
                .andExpect(jsonPath("$.city").value(vaccinationPoint.getCity()))
                .andExpect(jsonPath("$.address").value(vaccinationPoint.getAddress()));
    }

    @Test
    public void whenPut_saveVaccinationPoint_ReturnVaccinationPoint() throws Exception {
        VaccinationPoint vaccinationPoint = new VaccinationPoint(
                "Szent Borbála Kórház",
                "Komárom-Esztergom Megye",
                "2800 Tatabánya",
                "Dózsa Gy. út 77., \"H\" épület földszint");

        when(vaccinationPointService.save(any(VaccinationPoint.class))).thenReturn(vaccinationPoint);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccinationpoint")
                .content(objectMapper.writeValueAsString(vaccinationPoint))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccinationPoint.getName()))
                .andExpect(jsonPath("$.region").value(vaccinationPoint.getRegion()))
                .andExpect(jsonPath("$.city").value(vaccinationPoint.getCity()))
                .andExpect(jsonPath("$.address").value(vaccinationPoint.getAddress()));

        VaccinationPoint vaccinationPointUpdated = new VaccinationPoint(
                "Szent Borbála Kórház",
                "Komárom-Eszmeraldagom Megye",
                "2800 Tatabánya",
                "Dózsa Gy. út 77., \"H\" épület földszint");

        vaccinationPointUpdated.setId(1L);
        when(vaccinationPointService.update(any(VaccinationPoint.class))).thenReturn(vaccinationPointUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/vaccinationpoint")
                .content(objectMapper.writeValueAsString(vaccinationPointUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccinationPointUpdated.getName()))
                .andExpect(jsonPath("$.region").value(vaccinationPointUpdated.getRegion()))
                .andExpect(jsonPath("$.city").value(vaccinationPointUpdated.getCity()))
                .andExpect(jsonPath("$.address").value(vaccinationPointUpdated.getAddress()));
    }

    @Test
    public void whenGetFindByIdReturnVaccinationPoint() throws Exception {
        VaccinationPoint vaccinationPoint1 = new VaccinationPoint(
                "Szent Borbála Kórház",
                "Komárom-Esztergom Megye",
                "2800 Tatabánya",
                "Dózsa Gy. út 77., \"H\" épület földszint");

        VaccinationPoint vaccinationPoint2 = new VaccinationPoint(
                "Szent György Egyetemi Oktató Kórház",
                "Fejér Megye",
                "8000 Székesfehérvár",
                "Seregélyesi út 3. Rendelőintézet II. szint");

        when(vaccinationPointService.findById(1L)).thenReturn(java.util.Optional.of(vaccinationPoint1));
        when(vaccinationPointService.findById(2L)).thenReturn(java.util.Optional.of(vaccinationPoint2));
        this.mockMvc.perform(get("/vaccinationpoint/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccinationPoint1.getName()))
                .andExpect(jsonPath("$.region").value(vaccinationPoint1.getRegion()))
                .andExpect(jsonPath("$.city").value(vaccinationPoint1.getCity()))
                .andExpect(jsonPath("$.address").value(vaccinationPoint1.getAddress()));
        this.mockMvc.perform(get("/vaccinationpoint/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccinationPoint2.getName()))
                .andExpect(jsonPath("$.region").value(vaccinationPoint2.getRegion()))
                .andExpect(jsonPath("$.city").value(vaccinationPoint2.getCity()))
                .andExpect(jsonPath("$.address").value(vaccinationPoint2.getAddress()));
    }

    @Test
    public void whenDeleteByIdStatusIsOk() throws Exception {
        this.mockMvc.perform(delete("/vaccinationpoint/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
