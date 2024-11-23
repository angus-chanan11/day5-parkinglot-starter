package com.parkinglot;

import java.util.List;

public class StandardParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }
}
