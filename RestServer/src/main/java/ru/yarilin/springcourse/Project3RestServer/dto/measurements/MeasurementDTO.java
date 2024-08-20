package ru.yarilin.springcourse.Project3RestServer.dto.measurements;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementDTO {
    private int id;

    private Double value;

    private boolean raining;

    private SensorDTO sensor;

    private LocalDateTime createdAt;
}
