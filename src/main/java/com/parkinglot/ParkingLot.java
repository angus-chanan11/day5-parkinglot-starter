package com.parkinglot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingLot {
    private static final Integer DEFAULT_CAPACITY = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer occupiedPosition;
    private Integer capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(Integer capacity) {
        this.occupiedPosition = 0;
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        if (isPositionAvailable()) {
            Ticket ticket = new Ticket(this);
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

    public boolean isPositionAvailable() {
        return occupiedPosition < capacity;
    }

    public List<Car> getCurrentlyParkedCars() {
        return parkingRecords.entrySet().stream()
                .filter(entry -> !entry.getKey().isUsed())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Integer getRemainingPosition() {
        return capacity - occupiedPosition;
    }

    public Double getAvailablePositionRate() {
        return (double) getRemainingPosition() / capacity;
    }
}
