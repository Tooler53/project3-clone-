package ru.yarilin.springcourse.Project3RestServer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;
import ru.yarilin.springcourse.Project3RestServer.repositories.SensorRepository;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        log.info("Создание новго сенсора {}", sensor.getName());
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void delete(Sensor sensor) {
        sensorRepository.delete(sensor);
    }
}
