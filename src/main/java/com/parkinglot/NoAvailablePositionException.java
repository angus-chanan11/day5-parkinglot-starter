package com.parkinglot;

public class NoAvailablePositionException extends RuntimeException {
    public NoAvailablePositionException() {
        super("No available position.");
    }
}
