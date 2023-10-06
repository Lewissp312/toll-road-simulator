//The class that is used to process and store details about cars. Inherits methods from Vehicle
public class Car extends Vehicle{
    private int numberOfSeats;

    public Car(String vehicleReg,String vehicleMan, int numberOfSeats){
        super(vehicleReg,vehicleMan);
        this.numberOfSeats=numberOfSeats;
    }

    public int calculateBasicTripCost(){
        if (this.numberOfSeats<6) {
            return 500;
        } else{
            return 600;
        }
    }

    public String getVehicleReg(){
        return this.vehicleReg;
    }

    public String getVehicleMan(){
        return this.vehicleMan;
    }

    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }

    public String toString(){
        return String.format("The registration for this car is %s, " +
                        "the manufacturer is %s, the number of seats is %s " +
                        "and it costs %s at the toll road",
                this.vehicleReg,
                this.vehicleMan,
                this.numberOfSeats,
                this.calculateBasicTripCost());
    }

    public static void main(String[] args){
        //Test data: Car,GV14RRM,Selina,Eldred,Volkswagen,7,4000,NONE
        Car test1;
        test1 = new Car("GV14RRM","Volkswagen",7);
        System.out.println("Vehicle reg: " + test1.getVehicleReg());
        System.out.println("Vehicle manufacturer: " + test1.getVehicleMan());
        System.out.println("Number of seats: " + test1.getNumberOfSeats());
        System.out.println(test1);
        System.out.println("Cost with 7 seats: " + test1.calculateBasicTripCost());
        test1 = new Car("GV14RRM","Volkswagen",4);
        System.out.println("Cost with 4 seats: " + test1.calculateBasicTripCost());
    }
}
