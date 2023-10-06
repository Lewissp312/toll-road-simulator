//An abstract class that establishes the methods that Truck, Van and Car inherit
public abstract class Vehicle {
    protected String vehicleReg;
    protected String vehicleMan;

    public Vehicle(String vehicleReg,String vehicleMan){
        this.vehicleReg=vehicleReg;
        this.vehicleMan=vehicleMan;
    }

    public abstract int calculateBasicTripCost();

    public abstract String getVehicleReg();

    public abstract String getVehicleMan();
}
