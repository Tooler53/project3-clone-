package ru.yarilin.springcourse.Project3RestServer.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarilin.springcourse.Project3RestServer.exceptions.WatcherNotFoundException;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;
import ru.yarilin.springcourse.Project3RestServer.models.SensorProperties;
import ru.yarilin.springcourse.Project3RestServer.repositories.SensorPropertiesRepository;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class SensorPropertiesService {
    private final SensorPropertiesRepository sensorPropertiesRepository;
    private final WatchersService watchersService;

    public SensorPropertiesService(SensorPropertiesRepository sensorPropertiesRepository, WatchersService watchersService) {
        this.sensorPropertiesRepository = sensorPropertiesRepository;
        this.watchersService = watchersService;
    }

    @Transactional
    public void save(SensorProperties sensorProperties, int watcherId, Sensor sensor) {
        enrichSensorProperties(sensorProperties, watcherId, sensor);
        sensorPropertiesRepository.save(sensorProperties);
    }

    public Optional<SensorProperties> findOneBySensorId(int id){
        return sensorPropertiesRepository.findOneBySensorId(id);
    }

    private void enrichSensorProperties(SensorProperties sensorProperties, int watcherId, Sensor sensor) {
        sensorProperties.setSensor(sensor);
        sensorProperties.setWatcher(watchersService.findById(watcherId).orElseThrow(WatcherNotFoundException::new));
    }
}
