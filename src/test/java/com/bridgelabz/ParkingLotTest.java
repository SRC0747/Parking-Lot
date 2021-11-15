package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object car = null;
    private List<Integer> listOfEmptyParkingSlots;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(1);
        car = new Object();
    }

    @Test
    public void givenACar_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.park(car,0);
        boolean isParked = parkingLotSystem.isCarParked(car);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenACar_WhenAlreadyParked_ShouldThrowException() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(car, 0);
           parkingLotSystem.park(car, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenACar_WhenUnParked_ShouldReturnTrue() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.unPark(car);
        }, "No Such car Found");
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(parkingLotOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(car, 0);
            parkingLotSystem.park(car, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenACar_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car, 0);
            parkingLotSystem.park(car, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenACar_WhenParkingLotIsAvailable_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car, 0);
            parkingLotSystem.park(car, 1);
            parkingLotSystem.park(new Object(), 2);
            parkingLotSystem.unPark(car);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenParkingAttendant_AvailableSlot_WhereToParkCar() {
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        parkingLotSystem.park(car,0);
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        Assertions.assertEquals(0, listOfEmptyParkingSlots.size());
     }


    @Test
    public void givenACar_WhenFound_DriverCanGoHome() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(car, 0);
            parkingLotSystem.unPark(car);
            parkingLotSystem.findCar(car);
        }, "Not find the car to go home.");
    }

    @Test
    public void givenParkingLotSystem_WhenCheckedForVehicle_ShouldReturnVehicleSlot() {
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        car = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.park(car, 0);
        int slotNumber = parkingLotSystem.findCar(car);
        Assertions.assertEquals(0, slotNumber);
    }
}