package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private static final Integer CAPACITY = 10;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer occupiedSlot;

    public ParkingLot() {
        this.occupiedSlot = 0;
    }

    public Ticket park(Car car) {
        if (isSlotAvailable()) {
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            occupiedSlot++;
            return ticket;
        } else {
            System.out.printf(NO_AVAILABLE_POSITION);
            return null;
        }
    }

    public Car fetch(Ticket ticket) {
        Car fetchCar = parkingRecords.get(ticket);
        if (Objects.nonNull(fetchCar) && !ticket.isUsed()) {
            ticket.setUsed();
            occupiedSlot--;
            return parkingRecords.get(ticket);
        } else {
            System.out.printf(UNRECOGNIZED_PARKING_TICKET);
            return null;
        }
    }

    private boolean isSlotAvailable() {
        return occupiedSlot < CAPACITY;
    }
}
