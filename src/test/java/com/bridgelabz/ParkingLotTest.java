package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {
    Vehicle vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Maruti", "6598", "Black", "Sampriti");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Maruti", "6598", "Black", "Sampriti");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Maruti", "6598", "Black", "Sampriti");
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle));
    }

    public void givenAVehicle_WhenLotIsFullParked_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "7747", "Black", "Rakesh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "2255", "Red", "Himesh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "7747", "Black", "Rakesh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "2255", "Red", "Himesh");
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }


    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenToParkVehicleByAttendant_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "5698", "Brown", "Abhishek");
        parkingLotSystem.setCapacityOfParkingLot(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenADriver_WhenWantsToFindVehicle_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "4589", "Black", "Megha");
        Driver driver = new Driver();
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Object expectedVehicle = driver.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, expectedVehicle);
    }

    @Test
    void givenVehicles_WhenEvenlyParked_ShouldReturnTrue() {
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "2365", "Black", "Ratul");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "2356", "White", "Rakesh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenHandicappedPerson_WhenParkedVehicleByAttendantShouldReturnTrue() {
        parkingLotSystem.setCapacityOfParkingLot(5);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.HANDICAP,
                "Mercedes", "7747", "Red", "Nilima");
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenPoliceDepartment_WhenSearchForWhiteVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "7747", "White", "Rakesh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "5691", "White", "Rakesh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "5692", "Violet", "Rakesh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "5693", "White", "Rakesh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "5694", "White", "Rakesh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add("ParkingLot1: 0");
        expectedList.add("ParkingLot1: 2");
        expectedList.add("ParkingLot2: 0");
        expectedList.add("ParkingLot2: 1");
        List actualList = policeDepartment.getAllWhiteVehicles();
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    void givenPoliceDepartment_WhenThereAreNoWhiteVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "5747", "Yellow", "Megha");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Volvo", "4569", "Green", "Megha");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "22255", "Violet", "Megha");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "9878", "Black", "Megha");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "9879", "Orange", "Megha");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class, () -> policeDepartment.getAllWhiteVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenSearchForBlueToyotaVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "2352", "White", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "2353", "Blue", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "2354", "White", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "2355", "Blue", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                vehicle.getNumberPlate() + " Location = ParkingLot 1: " + 0);
        expectedList.add("Name of Parking Attendant = " + vehicle3.getParkingAttendantName() + " Plate Number = " +
                vehicle3.getNumberPlate() + " Location = ParkingLot 1: " + 1);
        expectedList.add("Name of Parking Attendant = " + vehicle5.getParkingAttendantName() + " Plate Number = " +
                vehicle5.getNumberPlate() + " Location = ParkingLot 1: " + 2);
        List actualList = policeDepartment.getBlueToyotaVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenThereAreNoBlueToyotaVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "5647", "Blue", "Souvik");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "8965", "Green", "Souvik");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "2265", "Violet", "Souvik");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "5697", "Black", "Souvik");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "5698", "Orange", "Souvik");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class, () -> policeDepartment.getBlueToyotaVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenBMWVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "5647", "Blue", "Rakesh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "2352", "White", "Rakesh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "2353", "Blue", "Rakesh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "2354", "White", "Rakesh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "2355", "Blue", "Rakesh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add(vehicle);
        expectedList.add(vehicle3);
        List actualList = policeDepartment.getAllBMWVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }


}
