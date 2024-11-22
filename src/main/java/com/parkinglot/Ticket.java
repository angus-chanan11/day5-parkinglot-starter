package com.parkinglot;

public class Ticket {
    private boolean isUsed;

    public Ticket() {
        this.isUsed = false;
    }

    public void setUsed() {
        this.isUsed = true;
    }

    public boolean isUsed() {
        return this.isUsed;
    }
}
