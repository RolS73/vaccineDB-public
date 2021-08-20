package com.codecool.vizsgaremekvaccinedatabase.integration;


import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PatientIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/patient";
    }

    private HttpEntity<Patient> createHttpEntityWithMediatypeJson(Patient patient) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(patient, headers);
    }

    @Test
    public void addNewPatient_emptyDatabase_shouldReturnSamePatient() {
        Patient testPatient = new Patient("János Pál", 28, "Male");
        Patient result = testRestTemplate.postForObject(baseUrl, testPatient, Patient.class);
        Assertions.assertEquals(testPatient, result);
    }

    @Test
    public void getPatients_emptyDatabase_returnsEmptyList() {
        List<Patient> patientList = List.of(testRestTemplate.getForObject(baseUrl, Patient[].class));
        assertEquals(0, patientList.size());
    }

    @Test
    public void getPatientById_withOnePostedPatient_returnsPatientWithSameId() {
        Patient testPatient = new Patient("János Pál", 28, "Male");
        testPatient = testRestTemplate.postForObject(baseUrl, testPatient, Patient.class);
        Patient result = testRestTemplate.getForObject(baseUrl + "/" + testPatient.getId(), Patient.class);
        assertEquals(testPatient.getId(), result.getId());
    }

    @Test
    public void updatePatient_withOnePostedPatient_returnsUpdatedPatient() {
        Patient testPatient =
                new Patient(
                        "János Pál",
                        28,
                        "Male");

        testPatient = testRestTemplate.postForObject(baseUrl, testPatient, Patient.class);

        testPatient.setFullName("Updated name");
        testRestTemplate.put(baseUrl, testPatient);
        Patient updatedPatient = testRestTemplate.getForObject(baseUrl + "/" + testPatient.getId(), Patient.class);

        assertEquals("Updated name", updatedPatient.getFullName());
    }

    @Test
    public void deletePatientById_withSomePostedPatients_getAllShouldReturnRemainingPatients() {
        Patient testPatient1 = new Patient("János Pál", 28, "Male");
        Patient testPatient2 = new Patient("Peter Crimson", 30, "Male");
        Patient testPatient3 = new Patient("Dóra Leonor", 22, "Female");
        List<Patient> testPatients = new ArrayList<>();
        testPatients.add(testPatient1);
        testPatients.add(testPatient2);
        testPatients.add(testPatient3);

        testPatients.forEach(testPatient ->
                testPatient.setId(testRestTemplate.postForObject(baseUrl, testPatient, Patient.class).getId())
        );

        testRestTemplate.delete(baseUrl + "/" + testPatient2.getId());
        testPatients.remove(testPatient2);

        List<Patient> remainingPatients = List.of(testRestTemplate.getForObject(baseUrl, Patient[].class));

        assertEquals(testPatients.size(), remainingPatients.size());
        for(int i = 0; i< testPatients.size(); i++){
            assertEquals(testPatients.get(i), remainingPatients.get(i));
        }
    }

    @Test
    public void savePatient_withInvalidName_shouldReturnBadRequest() {
        Patient testPatient = new Patient(null, 56, "Male");
        ResponseEntity<Patient> postResponse = testRestTemplate.postForEntity(baseUrl, testPatient, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
    }

    @Test
    public void savePatient_withInvalidAge_shouldReturnBadRequest() {
        Patient testPatient = new Patient("József István", -1, "Male");
        ResponseEntity<Patient> postResponse = testRestTemplate.postForEntity(baseUrl, testPatient, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
    }

    @Test
    public void savePatient_withInvalidGender_shouldReturnBadRequest() {
        Patient testPatient = new Patient("Péter Banderas", -1, null);
        ResponseEntity<Patient> postResponse = testRestTemplate.postForEntity(baseUrl, testPatient, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
    }

    @Test
    public void updatePatient_withInvalidName_shouldReturnBadRequest() {
        Patient testPatient = new Patient(null, 56, "Male");
        var httpEntity = createHttpEntityWithMediatypeJson(testPatient);
        ResponseEntity<Patient> putResponse = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
    }

    @Test
    public void updatePatient_withInvalidAge_shouldReturnBadRequest() {
        Patient testPatient = new Patient("József István", -1, "Male");
        var httpEntity = createHttpEntityWithMediatypeJson(testPatient);
        ResponseEntity<Patient> putResponse = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
    }

    @Test
    public void updatePatient_withInvalidGender_shouldReturnBadRequest() {
        Patient testPatient = new Patient("Péter Banderas", 28, null);
        var httpEntity = createHttpEntityWithMediatypeJson(testPatient);
        ResponseEntity<Patient> putResponse = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, Patient.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
    }
}
