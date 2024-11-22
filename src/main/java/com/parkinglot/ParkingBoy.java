package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }
}
