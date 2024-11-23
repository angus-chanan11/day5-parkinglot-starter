package com.parkinglot;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, new SmartParkingStrategy());
    }
}
