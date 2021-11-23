package com.bridgelabz;

import java.util.*;

/**
 * Purpose : To Simulate ParkingLot system
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotSystem {
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private static List<Vehicle> parkingLot1;
    private static List<Vehicle> parkingLot2;
    private static List<Vehicle> parkingLotForHandicapped;


    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
        parkingLotForHandicapped = new ArrayList<>();
    }

    /**
     * Purpose : This method is used to set the capacity of the parking lot.
     *
     * @param capacity defines the size of the parking lot.
     */
    public void setCapacityOfParkingLot(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * Purpose : This method is used to register the observers of parking lot.
     *
     * @param observer defines the observer of the parking lot.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle object to be parked
     * @throws ParkingLotException if the vehicle is already parked or if the lot
     *                             is full.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED,
                    "Vehicle is already parked");
        if (parkingLot1.size() <= actualCapacity && parkingLot2.size() <= actualCapacity
                && parkingLotForHandicapped.size() <= actualCapacity) {
            if(vehicle.getPersonType() == Vehicle.PersonType.HANDICAP) {
                parkingLotForHandicapped.add(vehicle);
            }
            else if (parkingLot1.size() > parkingLot2.size()) {
                parkingLot2.add(vehicle);
            } else {
                parkingLot1.add(vehicle);
            }
        }
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity) {
            for (ParkingLotObserver observers : observers) {
                observers.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.LOT_IS_FULL, "The Parking Lot is Full.");
        }
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle - object to be un-parked
     * @return boolean - True if the vehicle is un-parked
     * else false
     * @throws ParkingLotException if there is a null or nothing to be un-parked.
     */
    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle)) {
                parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle)) {
                parkingLotForHandicapped.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException
                (ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "There is no such vehicle");
    }


    /**
     * This method is used to check if the vehicle is parked or not.
     *
     * @param vehicle object to be checked if parked or not.
     * @return boolean true if vehicle is parked or else false.
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        return isParked;
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @param vehicle object to be checked if un-parked or not.
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        boolean isUnParked = true;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        return isUnParked;
    }

    /**
     * This method is used to find the vehicle in the parking lot.
     *
     * @param vehicle object to find.
     * @return object of vehicle if present.
     * @throws ParkingLotException if there is no such vehicle as passed in
     *                             the parameter in the parking lots.
     */
    public Vehicle findVehicle(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "No Such Vehicle Found");
    }

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotException when there are no white vehicles present in
     * the parking lots.
     */
    public List getWhiteColorVehiclePosition() throws ParkingLotException {
        ArrayList listOfWhiteVehicles = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getColor().equals("White"))
                listOfWhiteVehicles.add("ParkingLot1: "+ parkingLot1.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getColor().equals("White"))
                listOfWhiteVehicles.add("ParkingLot2: "+ parkingLot2.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getColor().equals("White"))
                listOfWhiteVehicles.add("HandicapParkingLot: "+ parkingLot2.indexOf(vehicle));
        }
        if(listOfWhiteVehicles.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No White Color Vehicle Found");
        return listOfWhiteVehicles;
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotException when there are no Blue Toyota vehicles present in
     * the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotException {
        ArrayList listOfBlueToyotaVehicles = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                listOfBlueToyotaVehicles.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = ParkingLot 1: " + parkingLot1.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                listOfBlueToyotaVehicles.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = ParkingLot 2: " + parkingLot2.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                listOfBlueToyotaVehicles.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = HandicapLot : " +
                        parkingLotForHandicapped.indexOf(vehicle));
            }
        }
        if(listOfBlueToyotaVehicles.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Blue Toyota Vehicle Found");
        return listOfBlueToyotaVehicles;
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotException when there are no BMW vehicles present in
     * the parking lots.
     */
    public List getBMWVehicles() throws ParkingLotException {
        ArrayList listOfBMWVehicles = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("BMW")) {
                listOfBMWVehicles.add(vehicle);
            }
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("BMW")) {
                listOfBMWVehicles.add(vehicle);
            }
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getName().equals("BMW")) {
                listOfBMWVehicles.add(vehicle);
            }
        }
        if(listOfBMWVehicles.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No BMW Vehicles Found");
        return listOfBMWVehicles;
    }

    /**
     * Purpose : This method is used to get the list of all Handicapped vehicles
     *
     * @return the list of Handicapped vehicles
     */
    public List getHandicappedVehicles(){
        ArrayList handicappedVehicles = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getVehicleType() == Vehicle.VehicleType.SMALL)
                handicappedVehicles.add(vehicle);
        }
        if(handicappedVehicles.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Found");
        return handicappedVehicles;
    }

    /**
     * Purpose : This method is used to get the list of all parked vehicles in the ParkingLot
     *
     * @return the list of parked vehicles
     */
    public List getAllVehicles(){
        ArrayList vehicleList = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            vehicleList.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot1) {
            vehicleList.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            vehicleList.add(vehicle);
        }
        if(vehicleList.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Present");
        return vehicleList;
    }
}