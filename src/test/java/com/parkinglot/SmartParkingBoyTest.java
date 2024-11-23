package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_return_ticket_and_park_to_first_lot_when_park_given_a_car_and_2_parking_lot_where_both_have_equal_amount_of_car(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot();
        secondParkingLot.park(new Car());
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertTrue(firstParkingLot.getCurrentlyParkedCars().contains(car));
        assertFalse(secondParkingLot.getCurrentlyParkedCars().contains(car));
    }

    @Test
    void should_return_ticket_and_park_to_second_lot_when_park_given_a_car_and_2_parking_lot_where_first_have_more_car_parked(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertFalse(firstParkingLot.getCurrentlyParkedCars().contains(car));
        assertTrue(secondParkingLot.getCurrentlyParkedCars().contains(car));
    }
}
