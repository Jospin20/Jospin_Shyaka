package Advanced_Motor_Vehicle_System;

class Vehicle {
    private String vehicleId, vehicleMake, vehicleModel, vehicleType;
    private int vehicleYear;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }
}

