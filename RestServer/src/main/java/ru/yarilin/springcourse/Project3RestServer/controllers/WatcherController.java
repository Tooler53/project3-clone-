package ru.yarilin.springcourse.Project3RestServer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.yarilin.springcourse.Project3RestServer.dto.watchers.WatchersDTO;
import ru.yarilin.springcourse.Project3RestServer.exceptions.WatchersNotAddException;
import ru.yarilin.springcourse.Project3RestServer.models.Watchers;
import ru.yarilin.springcourse.Project3RestServer.services.WatchersService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/watcher")
public class WatcherController {
    private final ModelMapper modelMapper;
    private final WatchersService watchersService;

    public WatcherController(ModelMapper modelMapper, WatchersService watchersService) {
        this.modelMapper = modelMapper;
        this.watchersService = watchersService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid WatchersDTO watchersDTO, BindingResult bindingResult) {
        Watchers watchers = convertToWatchers(watchersDTO);

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMsg = errors.stream()
                    .map(error -> error.getField() + " - " + error.getDefaultMessage())
                    .collect(Collectors.joining(";"));

            throw new WatchersNotAddException(errorMsg);
        }

        watchersService.create(watchers);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<WatchersDTO> getAllWatchers() {
        return watchersService.findAll().stream().map(this::convertToWatchersDTO).collect(Collectors.toList());
    }

    private WatchersDTO convertToWatchersDTO(Watchers watchers) {
        return modelMapper.map(watchers, WatchersDTO.class);
    }

    private Watchers convertToWatchers(WatchersDTO watchersDTO) {
        return modelMapper.map(watchersDTO, Watchers.class);
    }
}
