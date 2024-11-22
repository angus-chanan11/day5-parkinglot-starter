package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchCar = parkingRecords.get(ticket);
        if (Objects.nonNull(fetchCar) && !ticket.isUsed()) {
            ticket.setUsed();
            return parkingRecords.get(ticket);
        } else {
            return null;
        }
    }
}
