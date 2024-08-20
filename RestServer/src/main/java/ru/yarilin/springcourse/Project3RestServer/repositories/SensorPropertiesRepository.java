package ru.yarilin.springcourse.Project3RestServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yarilin.springcourse.Project3RestServer.models.SensorProperties;

import java.util.Optional;

@Repository
public interface SensorPropertiesRepository extends JpaRepository<SensorProperties, Integer> {
    Optional<SensorProperties> findOneBySensorId(int id);
}
