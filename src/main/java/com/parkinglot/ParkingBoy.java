package com.parkinglot;

import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this(parkingLots, new StandardParkingStrategy());
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(car, parkingLots);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot ticketParkingLot = ticket.getParkingLot();
        if (parkingLots.contains(ticketParkingLot)) {
            return ticketParkingLot.fetch(ticket);
        } else {
            throw new UnrecognizedParkingTicketException();
        }
    }
}
