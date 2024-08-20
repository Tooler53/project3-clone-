package ru.yarilin.springcourse.Project3RestServer.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;
import ru.yarilin.springcourse.Project3RestServer.services.SensorService;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "Сенсор с таким названием уже существует");
        }
    }
}
