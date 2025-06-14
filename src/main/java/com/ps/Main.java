package com.ps;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        VehicleDao vehicleDao = new VehicleDao();


        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Ford");
        vehicle.setModel("Fusion");
        vehicle.setYear(2016);
        vehicle.setColor("Red");
        vehicle.setMileage(103500);
        vehicle.setPrice(15000);
        vehicle.setVehicleType("Sedan");


        vehicleDao.addVehicle(vehicle);


        List<Vehicle> vehicles = vehicleDao.searchByMakeModel("Ford", "Fusion");
        for (Vehicle v : vehicles) {
            System.out.println(v.getMake() + " " + v.getModel());
        }


        vehicleDao.close();
    }
}