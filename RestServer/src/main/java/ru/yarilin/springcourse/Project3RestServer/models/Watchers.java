package ru.yarilin.springcourse.Project3RestServer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Watchers {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    @NotNull(message = "Поле Фамилия должно быть заполнено")
    @Min(value = 2, message = "Фамилия должна быть длинее 2-х символов")
    private String lastName;

    @Column(name = "first_name")
    @Min(value = 2, message = "Имя должно быть длинее 2-х символов")
    @NotNull(message = "Поле Имя должно быть заполнено")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private String position;

    public Watchers(String lastName, String firstName, String secondName, String position) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.position = position;
    }
}
