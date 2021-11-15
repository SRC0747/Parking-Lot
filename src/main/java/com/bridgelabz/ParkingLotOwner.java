package com.bridgelabz;

/**
 * Purpose : Show sign to ParkingLot owner informing that ParkingLot is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotOwner implements ParkingLotObserver {
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
     * @return the actualCapacity of ParkingLo
     */
    @Override
    public boolean isCapacityFull() {
        return this.actualCapacity;
    }
}
