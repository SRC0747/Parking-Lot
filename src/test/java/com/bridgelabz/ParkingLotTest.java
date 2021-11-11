package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object car = null;

    @BeforeEach
    public void setUp() throws Exception {
        car = new Object();
        parkingLotSystem = new ParkingLotSystem(1);
    }

    @Test
    public void givenACar_WhenParked_ShouldReturnTrue(){
        try{
            parkingLotSystem.park(car);
            boolean isParked = parkingLotSystem.isCarParked(car);
            Assertions.assertTrue(isParked);
        }catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenACar_WhenAlreadyParked_ShouldReturnFalse() {
        try{
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
        }catch (ParkingLotException e){
            Assertions.assertEquals("ParkingLot is full.", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenACar_WhenUnParked_ShouldReturnTrue() {
        try{
            parkingLotSystem.park(car);
            boolean isUnParked = parkingLotSystem.unPark(car);
            Assertions.assertTrue(isUnParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerOwner(owner);
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        boolean capacityFull = owner.IsCapacityFull();
        Assertions.assertTrue(capacityFull);
    }
}