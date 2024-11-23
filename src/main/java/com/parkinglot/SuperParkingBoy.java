package com.parkinglot;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy{
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, new SuperParkingStrategy());
    }
}
