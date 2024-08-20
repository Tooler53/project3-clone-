package ru.yarilin.springcourse.Project3RestServer.util;

public class SensorErrorResponse extends ErrorResponse {

    public SensorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
