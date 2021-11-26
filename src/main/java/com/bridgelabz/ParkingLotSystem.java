package com.bridgelabz;

import java.time.LocalTime;
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
    ArrayList handicappedVehicles = new ArrayList();
    ArrayList vehicleList = new ArrayList();

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
        parkingLotForHandicapped = new ArrayList<>();
        handicappedVehicles = new ArrayList<>();
        vehicleList = new ArrayList<>();
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
            if (vehicle.getPersonType() == Vehicle.PersonType.HANDICAP) {
                parkingLotForHandicapped.add(vehicle);
            } else if (parkingLot1.size() > parkingLot2.size()) {
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
        return isCheckVehicle(vehicle);
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @param vehicle object to be checked if un-parked or not.
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return isCheckVehicle(vehicle);
    }

    /**
     * Purpose : This method is used to check the vehicle
     *
     * @param vehicle object to be checked
     * @return isCheck for checking the situation of the vehicle
     */
    public boolean isCheckVehicle(Vehicle vehicle) {
        boolean isCheck = false;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle)) {
                isCheck = true;
                break;
            }
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle)) {
                isCheck = true;
                break;
            }
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle)) {
                isCheck = true;
                break;
            }
        }
        return isCheck;
    }

    /**
     * This method is used to find the vehicle in the parking lot.
     *
     * @param vehicle object to find.
     * @return object of vehicle if present.
     * @throws ParkingLotException if there is no such vehicle as passed in
     *                             the parameter in the parking lots.
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            for (Vehicle vehicle1 : parkingLot1) {
                if (vehicle1.equals(vehicle))
                    return parkingLot1.indexOf(vehicle1);
            }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                return parkingLot2.indexOf(vehicle1);
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                return parkingLotForHandicapped.indexOf(vehicle1);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                "No Such Vehicle Found");
    }


    /**
     * Purpose : This method is created for getting back the position of vehicle by providing vehicle color
     *
     * @param vehicleColor defines vehicle color for checking the equality of color entity present in Vehicle class
     * @return the index position in the parking lot
     * @throws ParkingLotException  throws exception when no such vehicle color is found
     */
    public int getVehiclePositionByColor(String vehicleColor) throws ParkingLotException {
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getColor().equals(vehicleColor))
                return parkingLot1.indexOf(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getColor().equals(vehicleColor))
                return parkingLot2.indexOf(vehicle);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.getColor().equals(vehicleColor))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                "No such vehicle found");
    }

    /**
     * Purpose : This method is created for getting back the position of vehicle by providing vehicle name
     *
     * @param vehicleName defines vehicle name for checking the equality of name entity present in Vehicle class
     * @return the index position in the parking lot
     * @throws ParkingLotException throws exception when no such vehicle name is found
     */
    public int getVehiclePositionByName(String vehicleName) throws ParkingLotException {
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals(vehicleName))
                return parkingLot1.indexOf(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals(vehicleName))
                return parkingLot2.indexOf(vehicle);
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getName().equals(vehicleName))
                return parkingLotForHandicapped.indexOf(vehicle);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                "No such vehicle found");
    }


    /**
     * Purpose : This method is created to know the vehicle plate number of blue color Toyota vehicle
     *
     * @param vehicle defines vehicle as parameter for checking the blue color Toyota vehicle's plate number
     * @return the vehicle number of that particular vehicle
     * @throws ParkingLotException throws exception when no such blue color Toyota is found
     */
    public String getBlueColorToyotaVehicleNumber(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle)
                && vehicle.getColor().equals("Blue")
                && vehicle.getName().equals("TOYOTA"))
            for (Vehicle vehicleNumberPlate : parkingLot1) {
                if (vehicleNumberPlate.equals(vehicle))
                    return vehicleNumberPlate.getNumberPlate();
            }
        for (Vehicle vehicleNumberPlate : parkingLot2) {
            if (vehicleNumberPlate.equals(vehicle))
                return vehicleNumberPlate.getNumberPlate();
        }
        for (Vehicle vehicleNumberPlate : parkingLotForHandicapped) {
            if (vehicleNumberPlate.equals(vehicle))
                return vehicleNumberPlate.getNumberPlate();
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                "No Such Vehicle Parked");
    }

    /**
     * Purpose : This method is used to get the list of all Handicapped vehicles
     *
     * @return the list of Handicapped vehicles
     */
    public List getHandicappedVehicles() {
        //ArrayList handicappedVehicles = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getVehicleType() == Vehicle.VehicleType.SMALL)
                handicappedVehicles.add(vehicle);
        }
        if (handicappedVehicles.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Found");
        return handicappedVehicles;
    }

    /**
     * Purpose : This method is used to get the list of all parked vehicles in the ParkingLot
     *
     * @return the list of parked vehicles
     */
    public List getAllVehicles() {
        //ArrayList vehicleList = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            vehicleList.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot1) {
            vehicleList.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            vehicleList.add(vehicle);
        }
        if (vehicleList.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Present");
        return vehicleList;
    }

    /**
     * Purpose : This method is created to get back the position of the vehicle which is parked for last 30 minutes
     *
     * @param time defines localTime of the parked vehicle
     * @return the index position of the vehicle in parking lot
     * @throws ParkingLotException throws exception when no vehicle is found parked for last 30 minutes
     */
    public int getLast30MinuteParkedVehicles(LocalTime time) throws ParkingLotException {
        for (Vehicle slot : parkingLot1) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLot1.indexOf(slot);
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLot2.indexOf(slot);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                "No such vehicle found");
    }

}
