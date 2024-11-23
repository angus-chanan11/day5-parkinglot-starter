package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) {
        ParkingLot parkingLotToParkAt = parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .max(Comparator.comparingInt(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLotToParkAt.park(car);
    }

}
