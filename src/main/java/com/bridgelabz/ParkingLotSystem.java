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
    private int currentCapacity;
    private  ArrayList<ParkingLotObserver> observers;
    private ArrayList<Object> car;
    private int actualCapacity;
    private ArrayList<Object> slots;
    //private Object car;

    /**
     * Constructor to access multiple observers and cars to access the capacity of ParkingLot and initialize the actualCapacity of ParkingLot to a particular value.
     * @param capacity defines the actualCapacity of ParkingLot
     */
    public ParkingLotSystem(int capacity){
        this.observers = new ArrayList<>();
        this.car = new ArrayList<>();
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : This method created to Park given car in Parking Lot
     *
     * @param car given car as parameter to observe the capacity of ParkingLot
     * @throws ParkingLotException : when the parking lot is full
     */
    public void park(Object car) throws  ParkingLotException {
        if (this.currentCapacity == this.actualCapacity) {
            for (ParkingLotObserver observer : observers)
                observer.capacityIsFull();
            throw new ParkingLotException("ParkingLot is full.");
        }
        this.currentCapacity++;
        this.car.add(car);
    }

    /**
     * Purpose : This method created to UnParked the car from parking lot
     *
     * @param car given vehicle as parameter to unPark the car
     * @throws ParkingLotException : when there is no vehicle to un park
     */
    public boolean unPark(Object car) throws ParkingLotException {
        if (this.car.contains(car)) {
            this.car.remove(car);
            return true;
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }

    /**
     * Purpose : To Check car is Parked in the ParkingLot
     *
     * @param car given Vehicle as parameter
     * @return car Equal to Given car
     */
    public boolean isCarParked(Object car) {
        return this.car.contains(car);
    }

    /**
     * Purpose : To Check whether the car is UnParked
     *
     * @param car given Vehicle as parameter
     * @return The Vehicle is UnParked
     */
    public boolean isCarUnParked(Object car) {
        return this.car == null;
    }

    /**
     * Purpose : Register Observer as Like Owner and AirportSecurity In List to be informed about ParkingLot
     *
     * @param observer To Add the observers in the List
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : Set the actualCapacity of the ParkingLot
     *
     * @param capacity defines the actual capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public void getAvailableSlot(Object slot) throws ParkingLotException{
        if (this.slots.contains(actualCapacity)) {
            for (ParkingLotObserver observer : observers){
                observer.capacityIsFull();
            }
            throw new ParkingLotException("No slot is remaining");
        }
        this.slots.add(slot);
    }
}
