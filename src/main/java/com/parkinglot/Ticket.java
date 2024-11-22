package com.parkinglot;

public class Ticket {
    private boolean isUsed;
    private ParkingLot parkingLot;

    public Ticket(ParkingLot parkingLot) {
        this.isUsed = false;
        this.parkingLot = parkingLot;
    }

    public void setUsed() {
        this.isUsed = true;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public ParkingLot getParkingLot() {
        return this.parkingLot;
    }
}
