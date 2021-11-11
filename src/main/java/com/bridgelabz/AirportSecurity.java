package com.bridgelabz;

/**
 * Show sign to Airport Security of ParkingLot  informing that ParkingLot is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */

public class AirportSecurity implements ParkingLotObserver {

    private boolean isFullCapacity;

    /**
     * capacityIsFull initialize the capacity of ParkingLot through which it indicates that ParkingLot is full
     */
        public void capacityIsFull() {
            isFullCapacity = true;
        }

    /**
     * isCapacityFull method is used to check the full capacity of ParkingLot
     * @return true that ParkingLot is full
     */
        public boolean isCapacityFull() {
            return this.isFullCapacity;
        }
}
