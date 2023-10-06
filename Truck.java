//The class that is used to process and store details about trucks. Inherits methods from Vehicle

public class Truck extends Vehicle{
    private int numTrailers;

    public Truck(String vehicleReg,String vehicleMan, int numTrailers){
        super(vehicleReg,vehicleMan);
        this.numTrailers=numTrailers;
    }

    public int calculateBasicTripCost(){
        if (this.numTrailers<2) {
            return 1250;
        } else{
            return 1500;
        }
    }

    public String getVehicleReg(){
        return this.vehicleReg;
    }

    public String getVehicleMan(){
        return this.vehicleMan;
    }

    public int getNumTrailers(){
        return this.numTrailers;
    }

    public String toString(){
        return String.format("The registration for this truck is %s, " +
                "the manufacturer is %s, the number of trailers is %s " +
                        "and it costs %s at the toll road",
                this.vehicleReg,
                this.vehicleMan,
                this.numTrailers,
                this.calculateBasicTripCost());
    }

    public static void main(String[] args){
        //Test data: Truck,YH13UPK,Margorie,Nall,Mercedes,2,5000,NONE
        Truck test1;
        test1 = new Truck("YH13UPK","Mercedes",2);
        System.out.println("Vehicle reg: " + test1.getVehicleReg());
        System.out.println("Vehicle manufacturer: " + test1.getVehicleMan());
        System.out.println("Number of trailers: " + test1.getNumTrailers());
        System.out.println(test1);
        System.out.println("Cost with 2 trailers: " +
                test1.calculateBasicTripCost());
        test1 = new Truck("YH13UPK","Mercedes",1);
        System.out.println("Cost with 1 trailer: " +
                test1.calculateBasicTripCost());
    }
}
