package ru.yarilin.springcourse.Project3RestServer.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarilin.springcourse.Project3RestServer.models.Watchers;
import ru.yarilin.springcourse.Project3RestServer.repositories.WatchersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WatchersService {
    private final WatchersRepository watchersRepository;

    public WatchersService(WatchersRepository watchersRepository) {
        this.watchersRepository = watchersRepository;
    }

    public Optional<Watchers> findById(int id) {
        return watchersRepository.findById(id);
    }

    public void create(Watchers watchers) {
        watchersRepository.save(watchers);
    }

    public List<Watchers> findAll() {
        return watchersRepository.findAll();
    }
}
