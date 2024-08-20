package ru.yarilin.springcourse.Project3RestServer.services;

import org.springframework.stereotype.Service;
import ru.yarilin.springcourse.Project3RestServer.exceptions.SensorNotFoundException;
import ru.yarilin.springcourse.Project3RestServer.models.Measurement;
import ru.yarilin.springcourse.Project3RestServer.models.SensorProperties;
import ru.yarilin.springcourse.Project3RestServer.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    private final SensorPropertiesService sensorPropertiesService;

    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService, SensorPropertiesService sensorPropertiesService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
        this.sensorPropertiesService = sensorPropertiesService;
    }

    public void create(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll().stream()/*.limit(50)*/.toList();
    }

    public int countRainingMeasurements() {
        return measurementRepository.countAllByRaining(true);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).orElseThrow(SensorNotFoundException::new));

        SensorProperties sensorProperties = sensorPropertiesService.findOneBySensorId(measurement.getSensor().getId()).get();

        if (!sensorProperties.isRainingSensor()) {//если нет сенсора дождя то показания не верны, а значит false
            measurement.setRaining(false);
        }
    }
}
