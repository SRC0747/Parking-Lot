package com.bridgelabz;

/**
 * Purpose : Create and add different properties(Vehicle name, Vehicle number and ParkingTime) of Vehicle parked in ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */
public class Vehicle {
    private final String name;
    private final String vehicleNumber;
    private final String parkingTime;
    private final String vehicleColour;

    /**
     * Constructor to access Vehicle name, Vehicle number and parkingTime of a Vehicle to a particular value
     *
     * @param name defines the name of the vehicle
     * @param vehicleNumber define the particular number of the given vehicle
     * @param parkingTime define the time when the vehicle is going to be parked
     * @param vehicleColour define the colour of the parked vehicle
     */
    public Vehicle(String name, String vehicleNumber, String parkingTime, String vehicleColour) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.parkingTime = parkingTime;
        this.vehicleColour = vehicleColour;
    }

    /**
     * Purpose : This method is used to get the name of the vehicle have to park in the ParkingLot
     *
     * @return name of the vehicle
     */
    public String getName() {
        return name;
    }

    /**
     * Purpose : This method is used to get the number of the vehicle have to park in the ParkingLot
     *
     * @return number of the vehicle
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Purpose : This method is used to get the parkingTime of the vehicle have to park in the ParkingLot
     *
     * @return parkingTime of the vehicle
     */
    public String getParkingTime() {
        return parkingTime;
    }

    /**
     * Purpose : This method is used to get the colour of the vehicle parked in the ParkingLot
     *
     * @return colour of the vehicle
     */
    public String getVehicleColour(){
        return vehicleColour;
    }

}
