package com.bridgelabz;


/**
 * Purpose : Driver can find the parked vehicle in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */
public class Driver {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * Purpose : This method is to search for the vehicle parked in the parking lot.
     *
     * @param vehicle defines the searched vehicle
     * @return object - the resultant vehicle after searching.
     */
    public Object searchVehicle(Vehicle vehicle){
        return parkingLotSystem.findVehicle(vehicle);
    }
}
