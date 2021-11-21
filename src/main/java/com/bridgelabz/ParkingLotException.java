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
     * @param parkingLotIsFull
     * @param message defines the exception message whether occurred
     */
    public ParkingLotException(ExceptionType parkingLotIsFull, String message) {
        super(message);
    }

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE,
        VEHICLE_ALREADY_EXIST, VEHICLE_IS_NOT_AVAILABLE }
}
