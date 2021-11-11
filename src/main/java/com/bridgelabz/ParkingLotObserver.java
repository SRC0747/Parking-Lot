package com.bridgelabz;

/**
 * Informs both the Observers(ParkingLot Owner and AirportSecurity) by informing that ParkingLot capacity is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */

public interface ParkingLotObserver {
    public void capacityIsFull();
}
