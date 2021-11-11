package com.bridgelabz;

/**
 * Throws an Exception message whether it is found that ParkingLot is full
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 11-11-2021
 */

public class ParkingLotException extends Exception {
    /**
     * ParkingLotException method throws the exception message of ParkingLot is full
     * @param message ParkingLot is full
     */
    public ParkingLotException(String message) {
        super(message);
    }
}
