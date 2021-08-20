package com.codecool.vizsgaremekvaccinedatabase.integration;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
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
public class VaccinationPointIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/vaccinationpoint";
    }

    private HttpEntity<VaccinationPoint> createHttpEntityWithMediatypeJson(VaccinationPoint vaccinationPoint) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(vaccinationPoint, headers);
    }

    @Test
    public void addNewVaccinationPoint_emptyDatabase_shouldReturnSameVaccinationPoint() {
        VaccinationPoint testVaccinationPoint =
                new VaccinationPoint(
                        "Szent Borbála Kórház",
                        "Komárom-Esztergom Megye",
                        "2800 Tatabánya",
                        "Dózsa Gy. út 77., \"H\" épület földszint");

        VaccinationPoint result = testRestTemplate.postForObject(baseUrl, testVaccinationPoint, VaccinationPoint.class);
        Assertions.assertEquals(testVaccinationPoint, result);
    }

    @Test
    public void getVaccinationPoints_emptyDatabase_returnsEmptyList() {
        List<VaccinationPoint> vaccinationPointList = List.of(testRestTemplate.getForObject(baseUrl, VaccinationPoint[].class));
        assertEquals(0, vaccinationPointList.size());
    }

    @Test
    public void getVaccinationPointById_withOnePostedVaccinationPoint_returnsVaccinationPointWithSameId() {
        VaccinationPoint testVaccinationPoint =
                new VaccinationPoint(
                        "Szent Borbála Kórház",
                        "Komárom-Esztergom Megye",
                        "2800 Tatabánya",
                        "Dózsa Gy. út 77., \"H\" épület földszint");

        //VaccinationPoint testVaccinationPointPosted = testRestTemplate.postForObject(baseUrl, testVaccinationPoint, VaccinationPoint.class);
        VaccinationPoint result = testRestTemplate.getForObject(baseUrl + "/" + testVaccinationPoint.getId(), VaccinationPoint.class);
        assertEquals(testVaccinationPoint.getId(), result.getId());
    }

    @Test
    public void updateVaccinationPoint_withOnePostedVaccinationPoint_returnsUpdatedVaccinationPoint() {
        VaccinationPoint testVaccinationPoint =
                new VaccinationPoint(
                        "Szent Borbála Kórház",
                        "Komárom-Esztergom Megye",
                        "2800 Tatabánya",
                        "Dózsa Gy. út 77., \"H\" épület földszint");

        testVaccinationPoint = testRestTemplate.postForObject(baseUrl, testVaccinationPoint, VaccinationPoint.class);

        testVaccinationPoint.setName("Updated name");
        testRestTemplate.put(baseUrl, testVaccinationPoint);
        VaccinationPoint updatedVaccinationPoint = testRestTemplate.getForObject(baseUrl + "/" + testVaccinationPoint.getId(), VaccinationPoint.class);

        assertEquals("Updated name", updatedVaccinationPoint.getName());
    }

    @Test
    public void deleteVaccinationPointById_withSomePostedVaccinationPoints_getAllShouldReturnRemainingVaccinationPoints() {
        VaccinationPoint testVaccinationPoint1 =
                new VaccinationPoint(
                        "Szent Borbála Kórház",
                        "Komárom-Esztergom Megye",
                        "2800 Tatabánya",
                        "Dózsa Gy. út 77., \"H\" épület földszint");

        VaccinationPoint testVaccinationPoint2 =
                new VaccinationPoint(
                        "Szent György Egyetemi Oktató Kórház",
                        "Fejér Megye",
                        "8000 Székesfehérvár",
                        "Seregélyesi út 3. Rendelőintézet II. szint");

        VaccinationPoint testVaccinationPoint3 =
                new VaccinationPoint(
                        "Szent Imre Egyetemi Oktatókórház",
                        "Pest Megye",
                        "1115 Budapest",
                        "Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia");

        List<VaccinationPoint> testVaccinationPoints = new ArrayList<>();
        testVaccinationPoints.add(testVaccinationPoint1);
        testVaccinationPoints.add(testVaccinationPoint2);
        testVaccinationPoints.add(testVaccinationPoint3);

        testVaccinationPoints.forEach(testVaccinationPoint ->
                testVaccinationPoint.setId(testRestTemplate.postForObject(baseUrl, testVaccinationPoint, VaccinationPoint.class).getId())
        );

        testRestTemplate.delete(baseUrl + "/" + testVaccinationPoint2.getId());
        testVaccinationPoints.remove(testVaccinationPoint2);

        List<VaccinationPoint> remainingVaccinationPoints = List.of(testRestTemplate.getForObject(baseUrl, VaccinationPoint[].class));

        assertEquals(testVaccinationPoints.size(), remainingVaccinationPoints.size());
        for(int i = 0; i< testVaccinationPoints.size(); i++){
            assertEquals(testVaccinationPoints.get(i), remainingVaccinationPoints.get(i));
        }
    }

    @Test
    public void saveVaccinationPoint_withInvalidData_shouldReturnBadRequest() {
        VaccinationPoint vaccinationPoint = new VaccinationPoint(
                null,
                "Pest Megye",
                "1115 Budapest",
                "Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia");

        ResponseEntity<VaccinationPoint> postResponse = testRestTemplate.postForEntity(baseUrl, vaccinationPoint, VaccinationPoint.class);

        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
    }

    @Test
    public void updateVaccinationPoint_withInvalidData_shouldReturnBadRequest() {
        VaccinationPoint vaccinationPoint = new VaccinationPoint(
                "Szent Imre Egyetemi Oktatókórház",
                "Pest Megye",
                null,
                "Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia");

        var httpEntity = createHttpEntityWithMediatypeJson(vaccinationPoint);
        ResponseEntity<VaccinationPoint> putResponse = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, VaccinationPoint.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
    }
}
