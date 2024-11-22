package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_car_when_fetch_twice_given_2_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        // When
        Car firstFetchedCar = parkingBoy.fetch(firstTicket);
        Car secondFetchedCar = parkingBoy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_error_when_fetch_given_non_existing_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        Ticket ticket = new Ticket();
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_park_given_no_position_remaining(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car car = new Car();
        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        // Then
        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }
}
