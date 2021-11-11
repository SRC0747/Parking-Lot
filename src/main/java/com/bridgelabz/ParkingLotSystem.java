package com.bridgelabz;

/**
 * Check whether the driver can park the car in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */

public class ParkingLotSystem {
    private int currentCapacity;
    private final int actualCapacity;
    private Object car;
    private ParkingLotOwner owner;

    /**
     * Constructor to initialize the actualCapacity and currentCapacity of ParkingLot to a particular value and zero accordingly
     * @param capacity initialize the actualCapacity of ParkingLot
     */
    public ParkingLotSystem(int capacity){
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * park method to check whether the ParkingLot is empty or  not and park the car if empty
     * @param car to park in the ParkingLot
     * @throws ParkingLotException exception message if ParkingLot is full
     */
    public void park(Object car) throws  ParkingLotException{
       // return true;
        if (this.currentCapacity == this.actualCapacity) {
            owner.capacityIsFull();
            throw new ParkingLotException("ParkingLot is full.");
        }
        this.currentCapacity++;
        this.car = car;
    }

    /**
     * unPark method to unPark the Parked car from the ParkingLot
     * @param car to check whether the car is already parked and also unPark the car
     * @return true if car can be unParked otherwise false
     */
    public boolean unPark(Object car) {
        if (car == null)
            return false;
        if (this.car.equals(car)){
            this.car = null;
            return true;
        }
        return false;
    }

    /**
     * isCarParked method check whether the car is already parked or not in the ParkingLot
     * @param car is already parked in ParkingLot
     * @return true if car is already parked otherwise false
     */
    public boolean isCarParked(Object car) {
        if (this.car.equals(car))
            return true;
        return false;
    }

    /**
     * registerOwner method informs the owner whether ParkingLot capacity is full
     * @param owner indicates the ParkingLot owner
     */
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }
}
