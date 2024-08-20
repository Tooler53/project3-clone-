package ru.yarilin.springcourse.Project3RestServer.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;
import ru.yarilin.springcourse.Project3RestServer.services.SensorService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SensorsControllerTest {
    private final String name = "TestSensor";

    @Autowired
    private SensorService sensorService;

    @Test
    void registrationTest(@Autowired TestRestTemplate restTemplate) {
        Map<String, Object> jsonBody = new HashMap<>();

        jsonBody.put("name", name);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonBody);

        String body = restTemplate.postForObject("/sensor/registration", request, String.class);

        assertThat(body).isEqualTo("\"OK\"");

        assertThat(sensorService.findByName(name).isPresent()).isTrue();
    }

    @AfterEach
    void clenUpDatabase() {
        Sensor sensor = sensorService.findByName(name).get();
        sensorService.delete(sensor);
    }
}
