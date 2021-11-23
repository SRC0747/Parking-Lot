package com.bridgelabz;

/**
 * Purpose : Show sign to ParkingLot owner informing that ParkingLot is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;


    /**
     * This method is to say that the capacity is full.
     */
    @Override
    public void capacityIsFull() {
        isFullCapacity = true;
    }
    /**
     * This method is to find if the capacity is available in the parking lot.
     */
    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    /**
     * This method is to quarry if the capacity of parking lot is
     * full or not.
     *
     * @return boolean - true if capacity is full else false.
     */
    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

}
