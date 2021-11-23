package com.bridgelabz;

/**
 * Purpose : To Simulate an Exception whether it is occurred in ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */

public class ParkingLotException extends RuntimeException {
    private final ExceptionType exceptionType;

    //constructor to initialize variables.
    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.exceptionType = type;
    }

    /**
     * Enum to define the type of exception.
     */
    public enum ExceptionType {
        LOT_IS_FULL, VEHICLE_NOT_FOUND, VEHICLE_ALREADY_PARKED
    }
}