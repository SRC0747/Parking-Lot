package com.bridgelabz;

import java.time.LocalTime;

/**
 * Purpose : Create and add different properties(Vehicle name, Vehicle number and ParkingTime) of Vehicle parked in ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */
public class Vehicle {
    private final String name;
    private final String numberPlate;
    private final String color;
    private final String parkingAttendantName;
    private final VehicleType vehicleType;
    private final PersonType personType;
    private LocalTime parkingTime;

    /**
     * Purpose : Constructor is used to initialize the variables
     *
     * @param type defines which type of vehicle(SMALL, MEDIUM and LARGE)
     * @param personType defines which type of person(NORMAL and HANDICAP)
     * @param name defines the vehicle name
     * @param numberPlate define the validate numberPate of the parked vehicle
     * @param color defines the colour of the vehicle
     * @param parkingAttendantName access the parkingAttendant of the vehicle
     * @param parkingTime defines the parking time of vehicle
     */
    public Vehicle(VehicleType type, PersonType personType, String name, String numberPlate, String color, String parkingAttendantName, LocalTime parkingTime) {
        this.name = name;
        this.numberPlate = numberPlate;
        this.color = color;
        this.parkingAttendantName = parkingAttendantName;
        this.vehicleType = type;
        this.personType = personType;
        this.parkingTime = parkingTime;
    }

    /**
     * Purpose : This method is used to get the type of Vehicle(Mercedes,Toyota,BMW etc.)
     *
     * @return the type of vehicle
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Purpose : This method is used to get the localTime of the Vehicle parked in the ParkingLot
     *
     * @return the localTime of parked Vehicle
     */
    public LocalTime getParkingTime() {
        return parkingTime;
    }

    /**
     * Purpose : This enum is used to access two types of person(NORMAL and HANDICAP)
     */
    enum PersonType{
        NORMAL, HANDICAP;
    }

    /**
     * Purpose : This method is used to get the input which type of person(NORMAL and HANDICAP)
     *
     * @return the type of the person
     */
    public PersonType getPersonType() {
        return personType;
    }

    /**
     * Purpose : This enum is used to access three types of vehicle(SMALL,MEDIUM and LARGE)
     */
    enum VehicleType{
        SMALL, MEDIUM, LARGE;
    }

    /**
     * Purpose : This method is used to get the name of the vehicle
     *
     * @return name of the Vehicle
     */
    public String getName() {
        return name;
    }

    /**
     * Purpose : This method is used to get the numberPlate of the vehicle
     *
     * @return the numberPlate of that particular Vehicle
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * Purpose : This method is used to get the colour of the vehicle
     *
     * @return the colour of the particular Vehicle
     */
    public String getColor() {
        return color;
    }

    /**
     * Purpose : This method is used to access the parkingAttendant of the vehicle
     *
     * @return the parkingAttendant of the particular Vehicle
     */
    public String getParkingAttendantName() {
        return parkingAttendantName;
    }
}
