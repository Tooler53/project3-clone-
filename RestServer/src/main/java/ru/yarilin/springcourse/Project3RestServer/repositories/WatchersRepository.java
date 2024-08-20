package ru.yarilin.springcourse.Project3RestServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yarilin.springcourse.Project3RestServer.models.Watchers;

@Repository
public interface WatchersRepository  extends JpaRepository<Watchers, Integer> {
}
