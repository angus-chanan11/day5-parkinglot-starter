package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private static final Integer CAPACITY = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer occupiedSlot;

    public ParkingLot() {
        this.occupiedSlot = 0;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        if (isSlotAvailable()) {
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            occupiedSlot++;
            return ticket;
        } else {
            throw new NoAvailablePositionException();
        }
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        Car fetchCar = parkingRecords.get(ticket);
        if (Objects.nonNull(fetchCar) && !ticket.isUsed()) {
            ticket.setUsed();
            occupiedSlot--;
            return parkingRecords.get(ticket);
        } else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    private boolean isSlotAvailable() {
        return occupiedSlot < CAPACITY;
    }
}
