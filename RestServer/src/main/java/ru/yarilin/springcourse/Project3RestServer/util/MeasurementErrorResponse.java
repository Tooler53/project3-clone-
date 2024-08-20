package ru.yarilin.springcourse.Project3RestServer.util;

public class MeasurementErrorResponse extends ErrorResponse {
    public MeasurementErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
