package ru.yarilin.springcourse.Project3RestServer.exceptions;

public class MeasurementNotAddException extends RuntimeException {
    public MeasurementNotAddException(String message) {
        super(message);
    }
}
