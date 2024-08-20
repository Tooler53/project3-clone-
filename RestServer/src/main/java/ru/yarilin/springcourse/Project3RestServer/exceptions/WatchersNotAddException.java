package ru.yarilin.springcourse.Project3RestServer.exceptions;

public class WatchersNotAddException extends RuntimeException {
    public WatchersNotAddException(String message) {
        super(message);
    }
}
