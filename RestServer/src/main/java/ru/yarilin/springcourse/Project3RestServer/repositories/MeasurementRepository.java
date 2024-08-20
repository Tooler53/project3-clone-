package ru.yarilin.springcourse.Project3RestServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yarilin.springcourse.Project3RestServer.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    public int countAllByRaining(boolean raining);
}
