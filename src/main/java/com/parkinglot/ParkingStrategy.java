package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot chooseParkingLot(List<ParkingLot> parkingLots) throws NoAvailablePositionException;
}
