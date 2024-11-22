package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException() {
        super("Unrecognized parking ticket.");
    }
}
