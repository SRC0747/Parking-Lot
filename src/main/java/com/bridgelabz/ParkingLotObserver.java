package com.bridgelabz;

/**
 * Purpose : Inform both the Observers(ParkingLot Owner and AirportSecurity) by informing that ParkingLot capacity is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public interface ParkingLotObserver {
    public void capacityIsFull();
    boolean isCapacityFull();
    /**
     * This method is to find if the capacity is available in the parking lot.
     */
    public void capacityIsAvailable();
}
