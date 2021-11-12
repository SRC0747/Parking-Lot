package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object car = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(1);
        car = new Object();
    }

    @Test
    public void givenACar_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(car);
            boolean isParked = parkingLotSystem.isCarParked(car);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenACar_WhenAlreadyParked_ShouldThrowException() {
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.park(car);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("ParkingLot is full.", e.getMessage());
        }
    }

    @Test
    public void givenACar_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(new Object());
            boolean isUnParked = parkingLotSystem.isCarUnParked(car);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
            boolean capacityFull = airportSecurity.isCapacityFull();
            Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenACar_WhenParkingLotAvailable_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();

        try {
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.unPark(car);
            Assertions.assertFalse(owner.isCapacityFull());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("ParkingLot is full.", e.getMessage());
        }
        Assertions.assertTrue(owner.isCapacityFull());
    }
}