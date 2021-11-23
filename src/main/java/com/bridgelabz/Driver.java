package com.bridgelabz;

public class Driver {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to search for the vehicle parked in the parking lot.
     *
     * @param vehicle to be searched
     * @return object - the resultant vehicle after searching.
     */
    public Object searchVehicle(Vehicle vehicle){
        return parkingLotSystem.findVehicle(vehicle);
    }
}
