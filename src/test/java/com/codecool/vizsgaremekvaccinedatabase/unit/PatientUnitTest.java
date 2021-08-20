package com.codecool.vizsgaremekvaccinedatabase.unit;

import com.codecool.vizsgaremekvaccinedatabase.controller.PatientController;
import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import com.codecool.vizsgaremekvaccinedatabase.service.PatientService;
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

@WebMvcTest(PatientController.class)
public class PatientUnitTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private PatientService patientService;

    @Test
    public void whenGetFindAllShouldReturnListOfPatientsTest() throws Exception {
        List<Patient> patients = List.of(
                new Patient("János Pál", 28, "Male"),
                new Patient("Dale Crimson", 30, "Male")
        );
        when(patientService.findAll()).thenReturn(patients);
        this.mockMvc.perform(get("/patient"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].fullName").value("János Pál"))
                .andExpect(jsonPath("$[0].age").value(28))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].fullName").value("Dale Crimson"))
                .andExpect(jsonPath("$[1].age").value(30))
                .andExpect(jsonPath("$[1].gender").value("Male"));
    }

    @Test
    public void whenPostSavePatientReturnPatient() throws Exception {
        Patient patient = new Patient("János Pál", 28, "Male");
        when(patientService.save(any(Patient.class))).thenReturn(patient);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/patient")
                .content(objectMapper.writeValueAsString(new Patient("János Pál", 28, "Male")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fullName").value("János Pál"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    public void whenPut_savePatient_ReturnPatient() throws Exception {
        Patient patient = new Patient("János Pál", 28, "Male");
        when(patientService.save(any(Patient.class))).thenReturn(patient);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/patient")
                .content(objectMapper.writeValueAsString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.fullName").value(patient.getFullName()))
                .andExpect(jsonPath("$.age").value(patient.getAge()))
                .andExpect(jsonPath("$.gender").value(patient.getGender()));

        Patient patientUpdated = new Patient("János Pál István", 28, "Male");
        patientUpdated.setId(1L);
        when(patientService.update(any(Patient.class))).thenReturn(patientUpdated);

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/patient")
                .content(objectMapper.writeValueAsString(patientUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(patientUpdated.getFullName()))
                .andExpect(jsonPath("$.age").value(patient.getAge()))
                .andExpect(jsonPath("$.gender").value(patientUpdated.getGender()));
    }

    @Test
    public void whenGetFindByIdReturnPatient() throws Exception {
        Patient patient1 = new Patient("János Pál", 28, "Male");
        Patient patient2 = new Patient("Dale Crimson", 30, "Male");
        when(patientService.findById(1L)).thenReturn(java.util.Optional.of(patient1));
        when(patientService.findById(2L)).thenReturn(java.util.Optional.of(patient2));
        this.mockMvc.perform(get("/patient/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("János Pál"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.gender").value("Male"));
        this.mockMvc.perform(get("/patient/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Dale Crimson"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    public void whenDeleteByIdStatusIsOk() throws Exception {
        this.mockMvc.perform(delete("/patient/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
