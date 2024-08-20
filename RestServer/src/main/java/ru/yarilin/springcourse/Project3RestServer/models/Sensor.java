package ru.yarilin.springcourse.Project3RestServer.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Sensor {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull(message = "Name не должно быть пустым")
    @Size(min = 3, max = 30, message = "Name должно быть в диапазоне между 3 and 30 символами")
    private String name;
}
