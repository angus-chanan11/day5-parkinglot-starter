package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private static final Integer CAPACITY = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer occupiedPosition;

    public ParkingLot() {
        this.occupiedPosition = 0;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        if (isPositionAvailable()) {
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            occupiedPosition++;
            return ticket;
        } else {
            throw new NoAvailablePositionException();
        }
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        Car fetchCar = parkingRecords.get(ticket);
        if (Objects.nonNull(fetchCar) && !ticket.isUsed()) {
            ticket.setUsed();
            occupiedPosition--;
            return parkingRecords.get(ticket);
        } else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    private boolean isPositionAvailable() {
        return occupiedPosition < CAPACITY;
    }
}
