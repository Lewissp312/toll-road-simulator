//Takes individual customer details (with the customer vehicle details having been converted in the toll road main class), processing and storing them. Using this class, the discount type of a customer can be set, funds can be added to their account, and a payment from their account can be made.
public class CustomerAccount implements Comparable{
    private String firstName;
    private String lastName;
    private int accBalance;
    private Vehicle accVehicle;
    //An enum can be used for the discounts as they can only be a few set values
    private enum Discounts {STAFF,FRIENDS_AND_FAMILY,NONE}
    private Discounts accDiscount;

    public CustomerAccount(String firstName, String lastName,int accBalance, Vehicle accVehicle){
        this.firstName=firstName;
        this.lastName=lastName;
        this.accBalance=accBalance;
        this.accVehicle=accVehicle;
        this.accDiscount=Discounts.NONE;
    }

    public void activateStaffDiscount(){
        this.accDiscount=Discounts.STAFF;
    }

    public void activateFriendsAndFamilyDiscount(){
        this.accDiscount=Discounts.FRIENDS_AND_FAMILY;
    }

    public void deactivateDiscount(){
        this.accDiscount=Discounts.NONE;
    }

    public void addFunds(int money){
        this.accBalance+=money;
    }

    public int makeTrip() throws InsufficientAccountBalanceException{
        int cost=accVehicle.calculateBasicTripCost();
        if (this.accDiscount==Discounts.STAFF){
            cost/=2; //50% discount
        } else if (this.accDiscount==Discounts.FRIENDS_AND_FAMILY){
            cost-=(cost/10); //10% discount
        }
        if (this.accBalance-cost<0){
            throw new InsufficientAccountBalanceException();
        } else{
            this.accBalance-=cost;
            return cost;
        }
    }



    public int compareTo(CustomerAccount acc2){
        int result = 0;
        if (this.accVehicle.vehicleReg.equals(acc2.accVehicle.vehicleReg)){
            //Would only happen if you accidentally compared a customer account with itself
            return result;
        } else{
            for(int i=0;i<this.accVehicle.vehicleReg.length();i++){
                if (this.accVehicle.vehicleReg.charAt(i)>acc2.accVehicle.vehicleReg.charAt(i)){
                    result = 1;
                    break;
                } else if (this.accVehicle.vehicleReg.charAt(i)<acc2.accVehicle.vehicleReg.charAt(i)){
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getAccBalance(){
        return this.accBalance;
    }

    public Vehicle getAccVehicle(){
        return this.accVehicle;
    }

    public Discounts getAccDiscount(){
        return this.accDiscount;
    }

    public String toString(){
        return String.format("First name: %s / Last name: %s / " +
                "Account balance: %s / Account vehicle details: %s " +
                "/ Account discount: %s ",this.firstName,this.lastName,
                this.accBalance,this.accVehicle,this.accDiscount);
    }

    public static void main(String[] args){
        //Test data: Car,KR60XNS,Gravin,Clawley,Lotus,2,999999,STAFF
        //Nice cameo appearance from Gavin
        //Test data: Van,YS06ESV,Rocky,Billie,Ford,700,8000,NONE
        //Test data: Truck,HE52JEL,Shelly,Mountjoy,Scammel,1,7000,NONE
        CustomerAccount test1Customer;
        CustomerAccount test2Customer;
        CustomerAccount test3Customer;
        Vehicle test1Vehicle;
        Vehicle test2Vehicle;
        Vehicle test3Vehicle;
        test1Vehicle = new Car("KR60XNS","Lotus",2);
        test2Vehicle = new Van("YS06ESV","Ford",700);
        test3Vehicle = new Truck("HE52JEL","Scammel",1);
        test1Customer = new CustomerAccount("Gravin","Clawley",999999,test1Vehicle);
        test2Customer = new CustomerAccount("Rocky","Billie",8000,test2Vehicle);
        test3Customer = new CustomerAccount("Shelly","Mountjoy",7000,test3Vehicle);
        System.out.println("First name: " + test1Customer.getFirstName());
        System.out.println("Last name: " + test1Customer.getLastName());
        System.out.println("Balance: " + test1Customer.getAccBalance());
        System.out.println("Vehicle details: " + test1Customer.getAccVehicle());
        System.out.println(test1Customer);
        test1Customer.activateStaffDiscount();
        System.out.println("Current discount: " + test1Customer.getAccDiscount());
        test1Customer.activateFriendsAndFamilyDiscount();
        System.out.println("Current discount: " + test1Customer.getAccDiscount());
        test1Customer.deactivateDiscount();
        System.out.println("Current discount: " + test1Customer.getAccDiscount());
        test1Customer.addFunds(1);
        System.out.println("Balance: " + test1Customer.getAccBalance());
        System.out.println("Comparing " + test1Customer.accVehicle.vehicleReg +
                " with itself: " + test1Customer.compareTo(test1Customer));
        System.out.println("Comparing " + test1Customer.accVehicle.vehicleReg +
                " with " + test2Customer.accVehicle.vehicleReg +
                ": " + test1Customer.compareTo(test2Customer));
        System.out.println("Comparing " + test1Customer.accVehicle.vehicleReg +
                " with " + test3Customer.accVehicle.vehicleReg +
                ": " + test1Customer.compareTo(test3Customer));


        //No discount

        try{
            System.out.println("\nCurrent balance: " +
                    test1Customer.getAccBalance());
            System.out.println("No discount cost: " +
                    test1Customer.makeTrip());
            System.out.println("Balance after purchase: " +
                    test1Customer.getAccBalance());
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient balance");
            System.out.println("Current balance: " +
                    test1Customer.getAccBalance());
        }

        //Staff discount

        test1Customer.activateStaffDiscount();
        try{
            System.out.println("\nCurrent balance: " +
                    test1Customer.getAccBalance());
            System.out.println("Staff discount cost: " +
                    test1Customer.makeTrip());
            System.out.println("Balance after purchase: " +
                    test1Customer.getAccBalance());
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient balance");
            System.out.println("Current balance: " +
                    test1Customer.getAccBalance());
        }

        //Friends and family discount

        test1Customer.activateFriendsAndFamilyDiscount();
        try{
            System.out.println("\nCurrent balance: " +
                    test1Customer.getAccBalance());
            System.out.println("F and F discount cost: " +
                    test1Customer.makeTrip());
            System.out.println("Balance after purchase: " +
                    test1Customer.getAccBalance());
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient balance");
            System.out.println("Current balance: " +
                    test1Customer.getAccBalance());
        }

        //Testing whether the insufficient balance exception is successfully caught

        test1Customer = new CustomerAccount("Gravin","Clawley",0,test1Vehicle);
        try{
            System.out.println("\nCurrent balance: " +
                    test1Customer.getAccBalance());
            System.out.println("Cost: " +
                    test1Customer.makeTrip());
            System.out.println("Balance after purchase: " +
                    test1Customer.getAccBalance());
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient balance, exception successful");
            System.out.println("Current balance: " +
                    test1Customer.getAccBalance());
        }

    }



}
