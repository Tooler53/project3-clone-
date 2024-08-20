package ru.yarilin.springcourse.Project3RestServer.dto.sensor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.yarilin.springcourse.Project3RestServer.dto.watchers.WatchersDTO;

@Setter
@Getter
public class RegistrationDTO {
    @NotEmpty(message = "Имя сенсора не должно быть пустым")
    @Size(min = 3, max = 30, message = "Name should not be between 2 and 30 characters")
    private String name;

    private SensorPropertiesDTO sensorProperties;

    private int watcher_id;
}
