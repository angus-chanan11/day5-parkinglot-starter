package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    Ticket park(Car car, List<ParkingLot> parkingLots) throws NoAvailablePositionException;
}
