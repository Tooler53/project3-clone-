package ru.yarilin.springcourse.Project3RestServer.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import ru.yarilin.springcourse.Project3RestServer.models.Measurement;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;
import ru.yarilin.springcourse.Project3RestServer.services.MeasurementService;
import ru.yarilin.springcourse.Project3RestServer.services.SensorService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MeasurementsControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MeasurementService measurementService;

    //todo бд не очищается после тестов, нужно менять подход
    @Test
    void addTest(@Autowired SensorService sensorService) {
        Map<String, Object> jsonBody = new HashMap<>();
        Map<String, String> sensorEntity = new HashMap<>();
        Random r = new Random();
        double randomValue = -100.0 + (100 - (-100)) * r.nextDouble();
        boolean randomRaining = r.nextBoolean();

        Sensor sensor = sensorService.findByName("NewSensot").get();

        sensorEntity.put("name", sensor.getName());
        jsonBody.put("value", randomValue);
        jsonBody.put("raining", randomRaining);
        jsonBody.put("sensor", sensorEntity);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonBody);

        String body = restTemplate.postForObject("/measurements/add", request, String.class);
        assertThat(body).isEqualTo("\"OK\"");

        List<Measurement> measurementList = measurementService.findAll();

        boolean present = measurementList.stream().anyMatch(measurement -> {
            int b = Double.compare(measurement.getValue(), randomValue);

            return b == 0 && measurement.isRaining() == randomRaining;
        });
        assertThat(present).isTrue();
    }

    @Test
    void rainyDaysCountTest() {
        long count = measurementService.countRainingMeasurements();
        String body = restTemplate.getForObject("/measurements/rainyDaysCount", String.class);
        assertThat(body).isEqualTo("{\"count\":" + count + "}");
    }

    @Test
    void getAllTest() throws JsonProcessingException {
        String body = restTemplate.getForObject("/measurements", String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(body);

        assertThat(jsonNode.size()).isEqualTo(measurementService.findAll().size());
    }
}
