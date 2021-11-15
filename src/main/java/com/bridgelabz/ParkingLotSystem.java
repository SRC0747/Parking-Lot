package com.bridgelabz;

import java.util.ArrayList;

/**
 * Check whether the driver can park the car in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotSystem {
    private int currentCapacity;
    private ArrayList<ParkingLotObserver> observers;
    private ArrayList<Object> cars;
    private int actualCapacity;
    private ArrayList<Object> slots;

    /**
     * Constructor to access multiple observers and vehicles to access the capacity of ParkingLot and initialize the actualCapacity of ParkingLot to a particular value.
     *
     * @param capacity defines the actualCapacity of ParkingLot
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
        initializeParkingLot();
    }

    public void initializeParkingLot() {
        this.cars = new ArrayList();
        for (int i = 0; i < this.actualCapacity; i++) {
            cars.add(i, null);
        }
    }

    /**
     * Purpose : This method created to Park given vehicle in Parking Lot
     *
     * @param car given vehicle as parameter to observe the capacity of ParkingLot
     * @param slot given slot as to check the available slots in ParkingLot
     * @throws ParkingLotException : when the parking lot is full
     */
    public void park(Object car, Integer slot) throws ParkingLotException {
        if (this.currentCapacity == this.actualCapacity) {
            for (ParkingLotObserver observer : observers)
                observer.capacityIsFull();
            throw new ParkingLotException("ParkingLot is full.");
        }
        if (this.cars.contains(car)) {
            throw new ParkingLotException("Vehicle Already exist.");
        }
        //this.currentCapacity++;
        //this.cars.add(car);
        this.cars.set(slot, car);
    }

    /**
     * Purpose : This method created to UnParked the car from parking lot
     *
     * @param car given vehicle as parameter to unPark the car
     * @throws ParkingLotException : when there is no car to un park
     */
    public boolean unPark(Object car) throws ParkingLotException {
        if (this.cars.contains(car)) {
            this.cars.remove(car);
            return true;
        }
        throw new ParkingLotException("No Such car Found");
    }

    /**
     * Purpose : To Check car is Parked in the ParkingLot
     *
     * @param car given car as parameter
     * @return the equal car if matched to Given car
     */
    public boolean isCarParked(Object car) {
        return this.cars.contains(car);
    }

    /**
     * Purpose : To Check whether the car is UnParked
     *
     * @param car given car as parameter
     * @return The car is UnParked
     */
    public boolean isCarUnParked(Object car) {
        return this.cars == null;
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

    /**
     * Purpose : This method is used to get and access the available slot to park car
     *
     * @return availableSlot whether it is available
     */
    public ArrayList<Integer> getAvailableListOfSlots() {
        ArrayList<Integer> availableSlots = new ArrayList<>();
        for (int i = 0; i < actualCapacity; i++) {
            if (this.cars.get(i) == null) {
                availableSlots.add(i);
            }
        }
        return availableSlots;
    }

    /**
     * Purpose : This method created to find the car whether it is parked in the parking lot
     *
     * @param car given car as parameter to find the car
     * @throws ParkingLotException : when no car is found there
     */
    public int findCar(Object car) throws ParkingLotException {
        if (this.cars.contains(car)) {
            return this.cars.indexOf(car);
        }
        throw new ParkingLotException("Not find the car to go home.");
    }

    /**
     * Purpose: To Find the Time when Vehicle Parked
     * @param vehicle is passed as parameter
     * @return parking time of the vehicle
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isCarParked(vehicle)) {
            return vehicle.getParkingTime();
        }
        return null;
    }
}
