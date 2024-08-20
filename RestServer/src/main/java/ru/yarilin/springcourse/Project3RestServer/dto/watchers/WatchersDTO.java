package ru.yarilin.springcourse.Project3RestServer.dto.watchers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchersDTO {
    @NotEmpty(message = "Поле Фамилия должно быть заполнено")
    @Min(value = 2, message = "Фамилия должна быть длинее 2-х символов")
    private String lastName;

    @NotEmpty(message = "Поле Имя должно быть заполнено")
    @Min(value = 2, message = "Имя должно быть длинее 2-х символов")
    private String firstName;

    private String secondName;

    private String position;
}
