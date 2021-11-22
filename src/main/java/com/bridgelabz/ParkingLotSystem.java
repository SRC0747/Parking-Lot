package com.bridgelabz;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Purpose : Simulate the ParkingLot System
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotSystem {
    private int currentCapacity;
    private ArrayList<ParkingLotObserver> observers;
    private ArrayList<Vehicle> vehicles;
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

    /**
     * Purpose : Initialize the list of Vehicles with null values
     */
    public void initializeParkingLot() {
        this.vehicles = new ArrayList();
        for (int i = 0; i < this.actualCapacity; i++) {
            vehicles.add(i, null);
        }
    }

    /**
     * Purpose : This method created to Park given vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter to observe the capacity of ParkingLot
     * @param slot    given slot as to check the available slots in ParkingLot
     * @throws ParkingLotException : when the parking lot is full
     */
    public void park(Vehicle vehicle, Integer slot) throws ParkingLotException {
        if (this.vehicles.size() == actualCapacity && !this.vehicles.contains(null)) {
            for (ParkingLotObserver parkingLotSystemObserver : observers)
                parkingLotSystemObserver.capacityIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL,
                    "Parking Lot is Full");
        }
        if (this.vehicles.contains(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_EXIST,
                    "Vehicle already exist");
        }
        this.vehicles.set(slot, vehicle);
    }

    /**
     * Purpose : This method created to UnParked the car from parking lot
     *
     * @param vehicle given vehicle as parameter to unPark the car
     * @throws ParkingLotException : when there is no car to un park
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such vehicle Found");
    }

    /**
     * Purpose : To Check vehicle is Parked in the ParkingLot
     *
     * @param vehicle given car as parameter
     * @return the equal car if matched to Given car
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicles.contains(vehicle);
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return The Vehicle is UnParked
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !this.vehicles.contains(vehicle);
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
     * Purpose : This method is used to get and access the available slot to park vehicle
     *
     * @return availableSlot whether it is available
     */
    public ArrayList<Integer> getAvailableListOfSlots() {
        ArrayList<Integer> availableSlots = new ArrayList<>();
        for (int i = 0; i < actualCapacity; i++) {
            if (this.vehicles.get(i) == null) {
                availableSlots.add(i);
            }
        }
        return availableSlots;
    }

    /**
     * Purpose : This method created to find the vehicle whether it is parked in the parking lot
     *
     * @param vehicle given car as parameter to find the car
     * @throws ParkingLotException : when no car is found there
     */
    public int findVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicles.contains(vehicle)) {
            return this.vehicles.indexOf(vehicle);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_IS_NOT_AVAILABLE,
                "Not find the vehicle to go home.");
    }

    /**
     * Purpose: This method is used to Find the Time when Vehicle Parked
     *
     * @param vehicle is passed as parameter
     * @return parking time of the vehicle
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isVehicleParked(vehicle)) {
            return vehicle.getParkingTime();
        }
        return null;
    }

    /**
     * Purpose: To Find the position of the white color Vehicle Parked
     *
     * @param vehicle is passed as parameter to find the position
     * @return the position of the white color vehicle
     * @throws ParkingLotException : when there is no vehicle found
     */
    public int getPositionOfWhiteColorVehicle(Vehicle vehicle) throws ParkingLotException{
        if (isVehicleParked(vehicle) && vehicle.getVehicleColour().equals("White"))
            for (Vehicle position : vehicles)
                if (position.equals(vehicle)) return vehicles.indexOf(position);
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "No white color vehicle found");
    }

    /**
     * Purpose: To Find the number plate of the blue color Vehicle Parked
     *
     * @param vehicle is passed as parameter to find the number plate of the vehicle
     * @return the position of the number plate of the vehicle
     * @throws ParkingLotException : when there is no vehicle found
     */
    public String getNumberPlateOfBlueColorVehicle(Vehicle vehicle) throws ParkingLotException{
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColour().equals("Blue")
                && vehicle.getName().equals("Toyota"))
            for (Vehicle numberPlate : vehicles) {
                if (numberPlate.equals(vehicle))
                    return numberPlate.getVehicleNumber();
            }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    public int getPositionOfBlueColorVehicle(Vehicle vehicle) throws ParkingLotException{
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColour().equals("Blue")
                && vehicle.getName().equals("Toyota"))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    /**
     * Purpose: To Find the all BMW Vehicle Parked in the parking lot system
     *
     * @param vehicle is passed as parameter to find the all BMW vehicle
     * @return the all BMW vehicles
     * @throws ParkingLotException : when there is no vehicle found
     */
    public int getBMWVehiclePosition(Vehicle vehicle) throws ParkingLotException{
        if (isVehicleParked(vehicle)
                && vehicle.getName().equals("BMW"))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    /**
     * Purpose : This method is checking the validity of vehicle number plates
     *
     * @param vehicleNumber : takes vehicle number as parameter for checking validation of number plate
     * @return : the matching value of that vehicle number if true or false
     */
    public boolean validateVehicleNumberPlate(String vehicleNumber) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][0-9]{2}[A-Z]{2}[0-9]{4}$");
        Matcher matcher = pattern.matcher(vehicleNumber);
        boolean number = matcher.matches();
        if (vehicleNumber.isEmpty())
            return false;
        return number;
    }
}
