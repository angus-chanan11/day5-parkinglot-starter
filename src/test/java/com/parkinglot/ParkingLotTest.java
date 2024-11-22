package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

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
    void should_return_error_when_fetch_given_non_existing_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_null_and_error_message_when_park_given_no_slot_remaining(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingLot.park(car);
        }
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNull(ticket);
        assertTrue(systemOut().contentEquals("No available position."));
    }
}
