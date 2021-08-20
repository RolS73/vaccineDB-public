package com.codecool.vizsgaremekvaccinedatabase.integration;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class VaccineDataIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/vaccinedata";
    }

    private HttpEntity<VaccineData> createHttpEntityWithMediatypeJson(VaccineData vaccineData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(vaccineData, headers);
    }

    @Test
    public void addNewVaccineData_emptyDatabase_shouldReturnSameVaccineData() {
        VaccineData testVaccineData =
                new VaccineData(
                        "Pfizer-BioNtech",
                        2, 18);

        VaccineData result = testRestTemplate.postForObject(baseUrl, testVaccineData, VaccineData.class);
        Assertions.assertEquals(testVaccineData, result);
    }

    @Test
    public void getVaccineData_emptyDatabase_returnsEmptyList() {
        List<VaccineData> vaccineDataList = List.of(testRestTemplate.getForObject(baseUrl, VaccineData[].class));
        Assertions.assertEquals(0, vaccineDataList.size());
    }

    @Test
    public void getVaccineDataByName_withOnePostedVaccineData_returnsVaccineDataWithSameId() {
        VaccineData testVaccineData =
                new VaccineData(
                        "Pfizer-BioNtech",
                        2, 18);

        testVaccineData = testRestTemplate.postForObject(baseUrl, testVaccineData, VaccineData.class);
        VaccineData result = testRestTemplate.getForObject(baseUrl + "/" + testVaccineData.getName(), VaccineData.class);
        Assertions.assertEquals(testVaccineData, result);
    }

    @Test
    public void updateVaccineData_withOnePostedVaccineData_returnsUpdatedVaccineData() {
        VaccineData testVaccineData =
                new VaccineData(
                        "Pfizer-BioNtech",
                        2, 18);

        testVaccineData = testRestTemplate.postForObject(baseUrl, testVaccineData, VaccineData.class);

        testVaccineData.setDosesNeeded(3);
        testRestTemplate.put(baseUrl, testVaccineData);
        VaccineData updatedVaccineData = testRestTemplate.getForObject(baseUrl + "/" + testVaccineData.getName(), VaccineData.class);

        Assertions.assertEquals(3, updatedVaccineData.getDosesNeeded());
    }

    @Test
    public void deleteVaccineDataById_withSomePostedVaccineData_getAllShouldReturnRemainingVaccineDatas() {
        VaccineData testVaccineData1 = new VaccineData("Pfizer-BioNtech", 2, 18);
        VaccineData testVaccineData2 = new VaccineData("Moderna", 2, 18);
        VaccineData testVaccineData3 = new VaccineData("Szputnyik", 2, 18);
        List<VaccineData> testVaccineDatas = new ArrayList<>();
        testVaccineDatas.add(testVaccineData1);
        testVaccineDatas.add(testVaccineData2);
        testVaccineDatas.add(testVaccineData3);

        testVaccineDatas.forEach(testVaccineData ->
                testVaccineData.setName(testRestTemplate.postForObject(baseUrl, testVaccineData, VaccineData.class).getName())
        );

        testRestTemplate.delete(baseUrl + "/" + testVaccineData2.getName());
        testVaccineDatas.remove(testVaccineData2);

        List<VaccineData> remainingVaccineDatas = List.of(testRestTemplate.getForObject(baseUrl, VaccineData[].class));

        Assertions.assertEquals(testVaccineDatas.size(), remainingVaccineDatas.size());
        for(int i = 0; i< testVaccineDatas.size(); i++){
            Assertions.assertEquals(testVaccineDatas.get(i), remainingVaccineDatas.get(i));
        }
    }
}
