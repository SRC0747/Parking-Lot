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

    //constructor to initialize variables.
    public Vehicle(VehicleType type, PersonType personType, String name, String numberPlate, String color, String parkingAttendantName, LocalTime parkingTime) {
        this.name = name;
        this.numberPlate = numberPlate;
        this.color = color;
        this.parkingAttendantName = parkingAttendantName;
        this.vehicleType = type;
        this.personType = personType;
        this.parkingTime = parkingTime;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalTime getParkingTime() {
        return parkingTime;
    }

    enum PersonType{
        NORMAL, HANDICAP;
    }

    public PersonType getPersonType() {
        return personType;
    }

    enum VehicleType{
        SMALL, MEDIUM, LARGE;
    }

    public String getName() {
        return name;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getColor() {
        return color;
    }

    public String getParkingAttendantName() {
        return parkingAttendantName;
    }

}
