package ru.yarilin.springcourse.Project3RestServer.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sensor_properties")
public class SensorProperties {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String model;

    @Column(name = "firmware_version")
    @Max(value = 30, message = "Версия прошивки должна быть в диапазоне между 3 and 30 символами")
    private String firmwareVersion;

    @Column(name = "has_raining_sensor")
    @NotNull(message = "Дождевой сенсор должен быть не пустым")
    private boolean rainingSensor;

    @OneToOne
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "watcher_id", referencedColumnName = "id")
    private Watchers watcher;

    public SensorProperties(String model, String firmwareVersion, boolean rainingSensor, Sensor sensor, Watchers watcher) {
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.rainingSensor = rainingSensor;
        this.sensor = sensor;
        this.watcher = watcher;
    }
}
