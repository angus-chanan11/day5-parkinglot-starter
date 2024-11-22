package com.parkinglot;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
        return firstAvailableParkingLot.park(car);
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
