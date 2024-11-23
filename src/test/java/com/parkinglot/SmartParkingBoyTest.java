package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    private static final int PARKING_LOT_CAPACITY = 10;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";

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

    @Test
    void should_return_correct_car_when_fetch_twice_given_2_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = smartParkingBoy.park(firstCar);
        Ticket secondTicket = smartParkingBoy.park(secondCar);
        // When
        Car firstFetchedCar = smartParkingBoy.fetch(firstTicket);
        Car secondFetchedCar = smartParkingBoy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_error_when_fetch_given_unrecognized_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        smartParkingBoy.park(car);
        Ticket ticket = new Ticket(firstParkingLot);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_park_given_no_position_remaining_in_all_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        parkCarToParkingLot(firstParkingLot, PARKING_LOT_CAPACITY);
        ParkingLot secondParkingLot = new ParkingLot();
        parkCarToParkingLot(secondParkingLot, PARKING_LOT_CAPACITY);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(car));
        // Then
        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }

    private void parkCarToParkingLot(ParkingLot parkingLot, int numberOfCars) {
        IntStream.range(0, numberOfCars)
                .forEach(iteration -> parkingLot.park(new Car()));
    }
}
