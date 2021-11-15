package com.bridgelabz;

/**
 * Purpose : Show sign to Airport Security of ParkingLot  informing that ParkingLot is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class AirportSecurity implements ParkingLotObserver {

    private boolean actualCapacity;

    /**
     * capacityIsFull initialize the capacity of ParkingLot through which it indicates that ParkingLot is full
     */
    @Override
        public void capacityIsFull() {
        actualCapacity = true;
        }

    /**
     * Purpose : This method is used to check the full capacity of ParkingLot
     *
     * @return the actualCapacity of ParkingLot
     */
    @Override
        public boolean isCapacityFull() {
            return this.actualCapacity;
        }

}
