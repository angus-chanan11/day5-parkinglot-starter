package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_a_car(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_car_when_fetch_twice_given_2_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Ticket secondTicket = parkingLot.park(secondCar);
        // When
        Car firstFetchedCar = parkingLot.fetch(firstTicket);
        Car secondFetchedCar = parkingLot.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_null_when_fetch_given_non_existing_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_null_when_fetch_given_used_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        // When
        Car doubleFetchCar = parkingLot.fetch(ticket);
        // Then
        assertNull(doubleFetchCar);
    }
}
