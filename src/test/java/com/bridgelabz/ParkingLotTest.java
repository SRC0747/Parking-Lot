package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    private List<Integer> listOfEmptySlots;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
    }
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        parkingLotSystem.park(vehicle, 0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00", "Blue");
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.park(vehicle, 1);
        }, "Parking Lot is Full");
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        parkingLotSystem.park(vehicle, 0);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
        parkingLotSystem.unPark(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleUnParked(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00", "Blue");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00", "Black");
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
            parkingLotSystem.unPark(vehicle);
        }, "Parking Lot is Full");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00", "white");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00", "Blue");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00", "Black");
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
        }, "Parking Lot is Full");
    }


    @Test
    public void givenVehicle_WhenParkingLotAvailableAndOwnerIsObserver_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00", "Blue");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00", "Black");
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
        });
        parkingLotSystem.unPark(vehicle1);
        Assertions.assertTrue(owner.isCapacityFull());
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() {
        listOfEmptySlots = parkingLotSystem.getAvailableListOfSlots();
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        parkingLotSystem.park(vehicle, 0);
        listOfEmptySlots = parkingLotSystem.getAvailableListOfSlots();
        Assertions.assertEquals(1, listOfEmptySlots.size());
    }

    @Test
    public void givenParkingLotSystem_WhenCheckedForVehicle_ShouldReturnVehicleSlot() {
        listOfEmptySlots = parkingLotSystem.getAvailableListOfSlots();
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        parkingLotSystem.park(vehicle, 0);
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(0, slotNumber);
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        parkingLotSystem.park(vehicle, 0);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("09:00", vehicleParkingTime);
    }

    @Test
    public void givenVehicle_WhenSearchedForWhiteColorVehicle_ShouldReturnTheVehicleLocation() {
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00", "Blue");
        parkingLotSystem.park(vehicle1, 0);
        parkingLotSystem.park(vehicle2, 1);
        int positionOfVehicle1 = parkingLotSystem.getPositionOfWhiteColorVehicle(vehicle1);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.getPositionOfWhiteColorVehicle(vehicle2));
        Assertions.assertEquals(0, positionOfVehicle1);
    }
}
