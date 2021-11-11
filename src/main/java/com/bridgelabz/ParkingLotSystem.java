package com.bridgelabz;

/**
 * Check whether the driver can park the car in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */
public class ParkingLotSystem {
    private Object car;

    public ParkingLotSystem(){
    }

    /**
     * park method to check whether to park the car in the ParkingLot
     * @param car to check the car can be parked or not
     * @return true if the car can be parked
     */
    public void park(Object car) throws  ParkingLotException{
       // return true;
        if (this.car != null)
            throw new ParkingLotException("ParkingLot is full.");
        this.car = car;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null)
            return false;
        if (this.car.equals(car)){
            this.car = null;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Object car) {
        if (this.car.equals(car))
            return true;
        return false;
    }
}
