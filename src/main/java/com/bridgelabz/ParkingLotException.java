package com.bridgelabz;

/**
 * Purpose : To Simulate an Exception whether it is occurred in ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotException extends RuntimeException {
    /**
     * Purpose : This method throws the exception message of ParkingLot is full
     *
     * @param message defines the exception message whether occurred
     */
    public ParkingLotException(String message) {
        super(message);
    }
}
