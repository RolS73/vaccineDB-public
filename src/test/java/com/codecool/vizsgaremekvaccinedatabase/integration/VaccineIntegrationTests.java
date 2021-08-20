package com.codecool.vizsgaremekvaccinedatabase.integration;

import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
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
public class VaccineIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/vaccine";
    }

    private HttpEntity<Vaccine> createHttpEntityWithMediatypeJson(Vaccine vaccine) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(vaccine, headers);
    }

    @Test
    public void addNewVaccine_emptyDatabase_shouldReturnSameVaccine() {
        Vaccine testVaccine = new Vaccine("Moderna", 2, 18);

        Vaccine result = testRestTemplate.postForObject(baseUrl, testVaccine, Vaccine.class);
        assertEquals(testVaccine, result);
    }

    @Test
    public void getVaccines_emptyDatabase_returnsEmptyList() {
        List<Vaccine> vaccineList = List.of(testRestTemplate.getForObject(baseUrl, Vaccine[].class));
        assertEquals(0, vaccineList.size());
    }

    @Test
    public void getVaccineById_withOnePostedVaccine_returnsVaccineWithSameId() {
        Vaccine testVaccine = new Vaccine("Moderna", 2, 18);

        testVaccine = testRestTemplate.postForObject(baseUrl, testVaccine, Vaccine.class);

        Vaccine result = testRestTemplate.getForObject(baseUrl + "/" + testVaccine.getId(), Vaccine.class);
        assertEquals(testVaccine, result);
    }

    @Test
    public void updateVaccine_withOnePostedVaccine_returnsUpdatedVaccine() {
        Vaccine testVaccine = new Vaccine("Moderna", 2, 18);

        testVaccine = testRestTemplate.postForObject(baseUrl, testVaccine, Vaccine.class);

        testVaccine.setDosesNeeded(2);

        testRestTemplate.put(baseUrl, testVaccine);
        Vaccine updatedVaccine = testRestTemplate.getForObject(baseUrl + "/" + testVaccine.getId(), Vaccine.class);

        assertEquals(testVaccine.getDosesNeeded(), updatedVaccine.getDosesNeeded());
    }

    @Test
    public void deleteVaccineById_withSomePostedVaccines_getAllShouldReturnRemainingVaccines() {
        Vaccine testVaccine1 = new Vaccine("Pfizer-BioNtech", 2, 18);
        Vaccine testVaccine2 = new Vaccine("Moderna", 2, 18);
        Vaccine testVaccine3 = new Vaccine("Szputnyik", 2, 18);
        List<Vaccine> testVaccines = new ArrayList<>();
        testVaccines.add(testVaccine1);
        testVaccines.add(testVaccine2);
        testVaccines.add(testVaccine3);

        testVaccines.forEach(testVaccine ->
                testVaccine.setId(testRestTemplate.postForObject(baseUrl, testVaccine, Vaccine.class).getId())
        );

        testRestTemplate.delete(baseUrl + "/" + testVaccine2.getId());
        testVaccines.remove(testVaccine2);

        List<Vaccine> remainingVaccines = List.of(testRestTemplate.getForObject(baseUrl, Vaccine[].class));

        assertEquals(testVaccines.size(), remainingVaccines.size());
        for(int i = 0; i< testVaccines.size(); i++){
            assertEquals(testVaccines.get(i), remainingVaccines.get(i));
        }
    }

    @Test
    public void saveVaccine_withInvalidData_shouldReturnBadRequest() {
        Vaccine vaccine = new Vaccine("Pfizer-BioNtech", 0, 18);

        ResponseEntity<Vaccine> postResponse = testRestTemplate.postForEntity(baseUrl, vaccine, Vaccine.class);

        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
    }

    @Test
    public void updateVaccinationPoint_withInvalidData_shouldReturnBadRequest() {
        Vaccine vaccine = new Vaccine(null, 2, 18);;

        var httpEntity = createHttpEntityWithMediatypeJson(vaccine);
        ResponseEntity<Vaccine> putResponse = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, Vaccine.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
    }
}
