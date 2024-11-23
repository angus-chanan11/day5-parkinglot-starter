package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .max(Comparator.comparingInt(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
    }

}
