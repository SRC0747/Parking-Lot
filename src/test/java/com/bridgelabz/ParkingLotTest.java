package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    private List<Integer> listOfEmptyParkingSlots;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle = new Object();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle,0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(vehicle, 0);
           parkingLotSystem.park(vehicle, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.unPark(vehicle);
        }, "No Such vehicle Found");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(parkingLotOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
           parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.park(vehicle, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.park(vehicle, 1);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsAvailable_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.park(vehicle, 1);
            parkingLotSystem.park(new Object(), 2);
            parkingLotSystem.unPark(vehicle);
        }, "ParkingLot is full.");
    }

    @Test
    public void givenParkingAttendant_AvailableSlot_WhereToParkVehicle() {
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        parkingLotSystem.park(vehicle,0);
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        Assertions.assertEquals(0, listOfEmptyParkingSlots.size());
     }


    @Test
    public void givenAVehicle_WhenFound_DriverCanGoHome() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.unPark(vehicle);
            parkingLotSystem.findVehicle(vehicle);
        }, "Not find the vehicle to go home.");
    }

    @Test
    public void givenParkingLotSystem_WhenCheckedForVehicle_ShouldReturnVehicleSlot() {
        listOfEmptyParkingSlots = parkingLotSystem.getAvailableListOfSlots();
        vehicle = new Vehicle("Maruti", "OR-05AB4321", "10:00");
        parkingLotSystem.park(vehicle, 0);
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(0, slotNumber);
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        vehicle = new Vehicle("Maruti", "OR-05AB4321", "10:00");
        parkingLotSystem.park(vehicle, 0);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime((Vehicle) vehicle);
        Assertions.assertEquals("10:00", vehicleParkingTime);
    }
}