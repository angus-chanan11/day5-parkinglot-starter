package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    public static final int BIG_PARKING_LOT_CAPACITY = 20;

    @Test
    void should_return_ticket_and_park_to_first_lot_when_park_given_a_car_and_2_parking_lot_where_both_have_equal_available_position_rate(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(BIG_PARKING_LOT_CAPACITY);
        secondParkingLot.park(new Car());
        secondParkingLot.park(new Car());
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = superParkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertTrue(firstParkingLot.getCurrentlyParkedCars().contains(car));
        assertFalse(secondParkingLot.getCurrentlyParkedCars().contains(car));
    }
}
