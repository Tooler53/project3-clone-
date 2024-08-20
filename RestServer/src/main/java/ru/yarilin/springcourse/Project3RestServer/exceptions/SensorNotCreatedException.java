package ru.yarilin.springcourse.Project3RestServer.exceptions;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String message) {
        super(message);
    }
}
