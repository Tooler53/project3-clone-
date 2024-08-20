package ru.yarilin.springcourse.Project3RestServer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.yarilin.springcourse.Project3RestServer.dto.measurements.AddRequestDTO;
import ru.yarilin.springcourse.Project3RestServer.dto.measurements.MeasurementDTO;
import ru.yarilin.springcourse.Project3RestServer.dto.measurements.RainyDaysCountDTO;
import ru.yarilin.springcourse.Project3RestServer.exceptions.MeasurementNotAddException;
import ru.yarilin.springcourse.Project3RestServer.exceptions.SensorNotFoundException;
import ru.yarilin.springcourse.Project3RestServer.models.Measurement;
import ru.yarilin.springcourse.Project3RestServer.services.MeasurementService;
import ru.yarilin.springcourse.Project3RestServer.util.MeasurementErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final ModelMapper modelMapper;

    private final MeasurementService measurementService;

    public MeasurementsController(ModelMapper modelMapper, MeasurementService measurementService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid AddRequestDTO addRequestDTO, BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(addRequestDTO);

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMsg = errors.stream()
                    .map(error -> error.getField() + " - " + error.getDefaultMessage())
                    .collect(Collectors.joining(";"));

            throw new MeasurementNotAddException(errorMsg);
        }
        measurementService.create(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysCountDTO getRainyDaysCount() {
        RainyDaysCountDTO rainyDaysCountDTO = new RainyDaysCountDTO();
        rainyDaysCountDTO.setCount(measurementService.countRainingMeasurements());

        return rainyDaysCountDTO;
    }

    private Measurement convertToMeasurement(AddRequestDTO addRequestDTO) {
        return modelMapper.map(addRequestDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddException ex) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(ex.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(SensorNotFoundException ex) {
        MeasurementErrorResponse response = new MeasurementErrorResponse("Сенсор не найден", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
