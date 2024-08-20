package sensor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.SwingWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import sensor.util.MeasurementDTO;

import java.util.*;

public class App {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static final String ENDPOINT = "http://localhost:8080/";
    public static final String METHOD_GET_LIST_ALL = "measurements";
    public static final String METHOD_POST_CREATE_MEASUREMENT = "measurements/add";

    public static void main(String[] args) throws JsonProcessingException {
        chart();
    }

    public static void chart() throws JsonProcessingException {
        String response = getAllMeasurement();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);

        List<MeasurementDTO> measurementDTOList = new ArrayList<>();

        for (JsonNode node : jsonNode) {
            measurementDTOList.add(new MeasurementDTO(node.get("value").asDouble(), node.get("createdAt").asText()));
        }

        List<Double> values = measurementDTOList.stream()
                .map(MeasurementDTO::getValue)
                .toList();

        CategoryChart categoryChart = new CategoryChart();
        org.knowm.xchart.CategoryChart chart = categoryChart.getChart(values);
        new SwingWrapper<>(chart).displayChart();
    }


    public static String getAllMeasurement() {
        String url = ENDPOINT + METHOD_GET_LIST_ALL;

        return restTemplate.getForObject(url, String.class);
    }

    public static void addMeasurementList() {
        for (int i = 0; i < 1000; i++) {
            Random r = new Random();
            double randomValue = -100.0 + (100 - (-100)) * r.nextDouble();
            boolean randomRaining = r.nextBoolean();
            addMeasurement(randomValue, randomRaining);
        }
    }


    private static void addMeasurement(double value, boolean raining) {
        String url = ENDPOINT + METHOD_POST_CREATE_MEASUREMENT;

        Map<String, Object> jsonBody = new HashMap<>();
        Map<String, String> sensorEntity = new HashMap<>();
        sensorEntity.put("name", "NewSensot");

        jsonBody.put("value", value);
        jsonBody.put("raining", raining);
        jsonBody.put("sensor", sensorEntity);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonBody);

        System.out.println(restTemplate.postForObject(url, request, String.class));
    }
}
