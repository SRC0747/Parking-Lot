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
        parkingLotSystem.park(car);
        boolean isParked = parkingLotSystem.isCarParked(car);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenACar_WhenAlreadyParked_ShouldThrowException() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(car);
           parkingLotSystem.park(car);
        }, "Parking lot is full.");
    }

    @Test
    public void givenACar_WhenUnParked_ShouldReturnTrue() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.unPark(car);
        }, "No Such Vehicle Found");
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(parkingLotOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(car);
           parkingLotSystem.park(new Object());
        }, "Parking lot is full.");
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
        }, "Parking lot is full.");
    }

    @Test
    public void givenACar_WhenParkingLotIsAvailable_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.unPark(car);
        }, "Parking lot is full.");
    }

    @Test
    public void givenParkingAttendant_AvailableSlot_WhereToParkCar() {
        Object slot = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        //parkingLotSystem.getAvailableSlot(slot);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.unPark(car);
            parkingLotSystem.getAvailableSlot(slot);
        }, "No slot is remaining");
    }

    @Test
    public void givenACar_WhenFound_DriverCanGoHome() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(car);
            parkingLotSystem.findCar(car);
        }, "Not find the vehicle to go home.");
    }
}