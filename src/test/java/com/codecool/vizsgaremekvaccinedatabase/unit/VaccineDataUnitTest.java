package com.codecool.vizsgaremekvaccinedatabase.unit;

import com.codecool.vizsgaremekvaccinedatabase.controller.VaccineDataController;
import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.service.VaccineDataService;
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

@WebMvcTest(VaccineDataController.class)
public class VaccineDataUnitTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private VaccineDataService vaccineDataService;

    @Test
    public void whenGetFindAllShouldReturnListOfVaccineDatasTest() throws Exception {
        List<VaccineData> vaccineDataList = List.of(
                new VaccineData("Pfizer-BioNtech", 2, 18),

                new VaccineData("Pfizer-BioNtech", 2, 18)
        );
        when(vaccineDataService.findAll()).thenReturn(vaccineDataList);
        this.mockMvc.perform(get("/vaccinedata"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].name").value(vaccineDataList.get(0).getName()))
                .andExpect(jsonPath("$[0].dosesNeeded").value(vaccineDataList.get(0).getDosesNeeded()))
                .andExpect(jsonPath("$[0].minAge").value(vaccineDataList.get(0).getMinAge()))

                .andExpect(jsonPath("$[1].name").value(vaccineDataList.get(1).getName()))
                .andExpect(jsonPath("$[1].dosesNeeded").value(vaccineDataList.get(1).getDosesNeeded()))
                .andExpect(jsonPath("$[1].minAge").value(vaccineDataList.get(1).getMinAge()));
    }

    @Test
    public void whenPostSaveVaccineDataReturnVaccineData() throws Exception {
        VaccineData vaccineData = new VaccineData("Pfizer-BioNtech", 2, 18);

        when(vaccineDataService.save(any(VaccineData.class))).thenReturn(vaccineData);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccinedata")
                .content(objectMapper.writeValueAsString(vaccineData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineData.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineData.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineData.getMinAge()));
    }

    @Test
    public void whenPut_saveVaccineData_ReturnVaccineData() throws Exception {
        VaccineData vaccineData = new VaccineData("Pfizer-BioNtech", 2, 18);
        when(vaccineDataService.save(any(VaccineData.class))).thenReturn(vaccineData);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/vaccinedata")
                .content(objectMapper.writeValueAsString(vaccineData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineData.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineData.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineData.getMinAge()));

        VaccineData vaccineDataUpdated = new VaccineData("Pfizer-BioNtech", 3, 18);
        when(vaccineDataService.update(any(VaccineData.class))).thenReturn(vaccineDataUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/vaccinedata")
                .content(objectMapper.writeValueAsString(vaccineDataUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineDataUpdated.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineDataUpdated.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineDataUpdated.getMinAge()));
    }

    @Test
    public void whenGetFindByIdReturnVaccineData() throws Exception {
        VaccineData vaccineData1 = new VaccineData("Pfizer-BioNtech", 2, 18);

        VaccineData vaccineData2 = new VaccineData("Moderna", 2, 18);

        when(vaccineDataService.findByName("Pfizer-BioNtech")).thenReturn(java.util.Optional.of(vaccineData1));
        when(vaccineDataService.findByName("Moderna")).thenReturn(java.util.Optional.of(vaccineData2));

        this.mockMvc.perform(get("/vaccinedata/Pfizer-BioNtech"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineData1.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineData1.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineData1.getMinAge()));
        this.mockMvc.perform(get("/vaccinedata/Moderna"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(vaccineData2.getName()))
                .andExpect(jsonPath("$.dosesNeeded").value(vaccineData2.getDosesNeeded()))
                .andExpect(jsonPath("$.minAge").value(vaccineData2.getMinAge()));
    }

    @Test
    public void whenDeleteByIdStatusIsOk() throws Exception {
        this.mockMvc.perform(delete("/vaccinedata/Moderna"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
