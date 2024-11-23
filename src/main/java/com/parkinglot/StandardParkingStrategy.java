package com.parkinglot;

import java.util.List;

public class StandardParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) throws NoAvailablePositionException {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
        return firstAvailableParkingLot.park(car);
    }
}
