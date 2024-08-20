package ru.yarilin.springcourse.Project3RestServer.dto.sensor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorPropertiesDTO {

    private String model;

    @Max(value = 30, message = "Версия прошивки должна быть не больше 30 символов")
    private String firmwareVersion;

    private boolean rainingSensor;
}
