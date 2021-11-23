package com.bridgelabz;

/**
 * Purpose : Give the attendant details of the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
    /**
     * This method is used when we need to park a vehicle by attendant.
     *
     * @param vehicle - the vehicle to be parked.
     */
    public void parkVehicleByAttendant(Vehicle vehicle) {
        parkingLotSystem.park(vehicle);
    }
}
