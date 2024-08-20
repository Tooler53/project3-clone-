package ru.yarilin.springcourse.Project3RestServer.dto.measurements;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.yarilin.springcourse.Project3RestServer.models.Sensor;

@Getter
@Setter
public class AddRequestDTO {
    @NotEmpty
    @DecimalMin(value = "-100.0", message = "Показания должны быть больше -100")
    @DecimalMax(value = "100.0", message = "Показания должны быть меньше 100")
    private Double value;

    @NotEmpty(message = "Raining должен быть не пустым")
    private boolean raining;

    @NotEmpty(message = "Sensor должно быть заполнено")
    private Sensor sensor;
}
