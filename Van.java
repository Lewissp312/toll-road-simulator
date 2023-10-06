//The class that is used to process and store details about vans. Inherits methods from Vehicle
public class Van extends Vehicle{
    private int payload;

    public Van(String vehicleReg,String vehicleMan, int payload){
        super(vehicleReg,vehicleMan);
        this.payload=payload;
    }

    public int calculateBasicTripCost(){
        if (this.payload<=600){
            return 500;
        } else if(this.payload<=800){
            return 750;
        } else{
            return 1000;
        }
    }

    public String getVehicleReg(){
        return this.vehicleReg;
    }

    public String getVehicleMan(){
        return this.vehicleMan;
    }

    public int getPayload(){
        return this.payload;
    }

    public String toString(){
        return String.format("The registration for this van is %s, " +
                        "the manufacturer is %s, the payload is %s KG " +
                        "and it costs %s at the toll road",
                this.vehicleReg,
                this.vehicleMan,
                this.payload,
                this.calculateBasicTripCost());
    }

    public static void main(String[] args){
        //Test data: Van,ZM02EUX,Toby,Vicini,Ford,700,19000,FRIENDS_AND_FAMILY
        Van test1;
        test1 = new Van("ZM02EUX","Ford",700);
        System.out.println("Vehicle reg: " + test1.getVehicleReg());
        System.out.println("Vehicle manufacturer: " + test1.getVehicleMan());
        System.out.println("Payload: " + test1.getPayload());
        System.out.println(test1);
        System.out.println("Cost with a payload of 700: " +
                test1.calculateBasicTripCost());
        test1 = new Van("ZM02EUX","Ford",600);
        System.out.println("Cost with a payload of 600: " +
                test1.calculateBasicTripCost());
        test1 = new Van("ZM02EUX","Ford",850);
        System.out.println("Cost with a payload of 850: " +
                test1.calculateBasicTripCost());
    }
}
