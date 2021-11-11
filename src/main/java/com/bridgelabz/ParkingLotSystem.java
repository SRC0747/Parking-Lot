package com.bridgelabz;

import java.util.ArrayList;

/**
 * Check whether the driver can park the car in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */

public class ParkingLotSystem {
    private  ArrayList<ParkingLotObserver> observers;
    private ArrayList<Object> cars;
    private int actualCapacity;
    private Object car;

    /**
     * Constructor to access multiple observers and cars to access the capacity of ParkingLot and initialize the actualCapacity of ParkingLot to a particular value.
     * @param capacity defines the actualCapacity of ParkingLot
     */
    public ParkingLotSystem(int capacity){
        this.observers = new ArrayList<>();
        this.cars = new ArrayList<>();
        //this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * park method to check whether the ParkingLot is empty or  not and park the car if empty
     * @param car to park in the ParkingLot
     * @throws ParkingLotException exception message if ParkingLot is full
     */
    public void park(Object car) throws  ParkingLotException {
       // return true;
        if (this.cars.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers){
                observer.capacityIsFull();
            }
            throw new ParkingLotException("ParkingLot is full.");
        }
    }
    public boolean parkCar(Object car) {
        return true;
    }
    /**
     * unPark method to unPark the Parked car from the ParkingLot
     * @param car to check whether the car is already parked and also unPark the car
     * @return true if car can be unParked otherwise false
     */
    public boolean unPark(Object car) {
        if (car == null)
            return false;
        if (this.cars.contains(car)){
            //this.vehicle = null;
            this.cars.remove(car);
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
     * registerOwner method informs the observer whether ParkingLot capacity is full
     * @param observer indicates the ParkingLot observer including both the owner and security
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
       // this.owner = owner;
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /*public void registerSecurity(AirportSecurity airportSecurity) {
        this.security = airportSecurity;
    }*/
}
