package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";
    private static final int PARKING_LOT_CAPACITY = 10;

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
    void should_return_error_when_fetch_given_unrecognized_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        parkingBoy.park(car);
        Ticket ticket = new Ticket(parkingLot);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket_and_a_parking_lot(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        parkCarToParkingLot(parkingLot, PARKING_LOT_CAPACITY);
        List<ParkingLot> parkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        // Then
        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }

    @Test
    void should_return_ticket_and_park_to_first_lot_when_park_given_a_car_and_2_parking_lots(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertTrue(firstParkingLot.getCurrentlyParkedCars().contains(car));
        assertFalse(secondParkingLot.getCurrentlyParkedCars().contains(car));
    }

    @Test
    void should_return_ticket_and_park_to_second_lot_when_park_given_a_car_and_first_lot_full_and_second_lot_available(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        parkCarToParkingLot(firstParkingLot, PARKING_LOT_CAPACITY);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
        assertFalse(firstParkingLot.getCurrentlyParkedCars().contains(car));
        assertTrue(secondParkingLot.getCurrentlyParkedCars().contains(car));
    }

    @Test
    void should_return_correct_car_when_fetch_twice_given_2_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        parkCarToParkingLot(firstParkingLot, PARKING_LOT_CAPACITY - 1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
    void should_return_error_when_fetch_given_unrecognized_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        parkingBoy.park(car);
        Ticket ticket = new Ticket(firstParkingLot);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        // Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_used_ticket_and_2_parking_lot(){
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        // When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        // When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        // Then
        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }

    private void parkCarToParkingLot(ParkingLot parkingLot, int numberOfCars) {
        IntStream.range(0, numberOfCars)
                .forEach(iteration -> parkingLot.park(new Car()));
    }
}
