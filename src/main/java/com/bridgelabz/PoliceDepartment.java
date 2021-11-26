package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Purpose : Get the details of all white vehicles, blue Toyota, BMW and all Handicapped vehicles parked in the ParkingLot
 *
 * @author Sampriti Roy Chowdhury
 * @version 0.0.1
 * @since 14-11-2021
 */
public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * Purpose : This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotException when there are no white vehicles present in
     * the parking lots.
     */
    public int getAllWhiteVehicles() throws ParkingLotException{
        return parkingLotSystem.getVehiclePositionByColor("White");
    }


    /**
     * purpose : This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotException when there are no BMW vehicles present in
     * the parking lots.
     */
    public int getAllBMWVehicles() throws ParkingLotException{
        return parkingLotSystem.getVehiclePositionByName("BMW");
    }

    /**
     * Purpose : This method is used to get list of all Handicapped vehicles parked in ParkingLot
     *
     * @return list of all Handicapped vehicles
     */
    public List getAllHandicappedVehicles(){
        return parkingLotSystem.getHandicappedVehicles();
    }

    /**
     * Purpose : This method is used to get list of all parked vehicles with validate number plates.
     *
     * @return list of vehicles parked
     */
    public List vehicleNumberValidate(){
        List<Vehicle> parkedVehicles = new ArrayList();
        List<Vehicle> vehicleslist = new ArrayList();
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{2,4}$");
        parkedVehicles = parkingLotSystem.getAllVehicles();
        for(Vehicle vehicle : parkedVehicles){
            Matcher matcher = pattern.matcher(vehicle.getNumberPlate());
            if(matcher.matches() == false)
                vehicleslist.add(vehicle);
        }
        if(vehicleslist.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Invalid Vehicles Present");
        return vehicleslist;
    }
}
