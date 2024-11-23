package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    private static final int BIG_PARKING_LOT_CAPACITY = 20;
    private static final int DEFAULT_PARKING_LOT_CAPACITY = 10;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    @Test
    void should_return_ticket_and_park_to_first_lot_when_park_given_a_car_and_2_parking_lot_where_both_have_equal_available_position_rate(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(BIG_PARKING_LOT_CAPACITY);
        parkCarToParkingLot(secondParkingLot, 2);
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

    @Test
    void should_return_ticket_and_park_to_second_lot_when_park_given_a_car_and_2_parking_lot_where_second_have_higher_available_position_rate(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot(BIG_PARKING_LOT_CAPACITY);
        parkCarToParkingLot(firstParkingLot, BIG_PARKING_LOT_CAPACITY - DEFAULT_PARKING_LOT_CAPACITY);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = superParkingBoy.park(car);
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
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = superParkingBoy.park(firstCar);
        Ticket secondTicket = superParkingBoy.park(secondCar);
        // When
        Car firstFetchedCar = superParkingBoy.fetch(firstTicket);
        Car secondFetchedCar = superParkingBoy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    private void parkCarToParkingLot(ParkingLot parkingLot, int numberOfCars) {
        IntStream.range(0, numberOfCars)
                .forEach(iteration -> parkingLot.park(new Car()));
    }

    @Test
    void should_return_error_when_fetch_given_unrecognized_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        superParkingBoy.park(car);
        Ticket ticket = new Ticket(firstParkingLot);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }
}
