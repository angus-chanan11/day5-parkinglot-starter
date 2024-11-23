package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingStrategy implements ParkingStrategy{
    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) throws NoAvailablePositionException {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isPositionAvailable)
                .max(Comparator.comparingInt(currentParkingLot ->
                    currentParkingLot.getCapacity() - currentParkingLot.getCurrentlyParkedCars().size()
                ))
                .orElseThrow(NoAvailablePositionException::new);
        return firstAvailableParkingLot.park(car);
    }
}
