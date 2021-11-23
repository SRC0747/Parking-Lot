package com.bridgelabz;

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
