//The class that handles operations involving the hash map that stores customers.
//Customers can be added, looked up and charged for a toll road usage.
//This class also stores the money made from all the transactions.
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;

public class TollRoad {
    //Customer accounts are stored using a hash map.
    //The most common operation with customer accounts is the looking up of their registration numbers.
    //With a hash map, the registration numbers can be stored in association with their customer accounts,leading to much faster (constant time) lookup times.
    private HashMap<String,CustomerAccount> accounts;
    private int moneyMade;

    public TollRoad(HashMap<String,CustomerAccount> accounts,int moneyMade){
        this.accounts=accounts;
        this.moneyMade=moneyMade;
    }
    

    public void addCustomer(CustomerAccount acc){
        this.accounts.put(acc.getAccVehicle().getVehicleReg(),acc);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException{
        if (this.accounts.containsKey(regNum)){
            return this.accounts.get(regNum);
        } else{
            throw new CustomerNotFoundException();
        }
    }

    public void chargeCustomer(String regNum) throws CustomerNotFoundException, InsufficientAccountBalanceException{
        this.moneyMade+=this.findCustomer(regNum).makeTrip();
    }

    public HashMap<String,CustomerAccount> getAccounts(){
        return this.accounts;
    }

    public int getMoneyMade(){
        return this.moneyMade;
    }

    //The for loop below was adapted.
    //I changed the variable names and the styling of the output.
    //I got it from here:
    // https://sentry.io/answers/iterate-hashmap-java/
    //This special iteration method is needed as hash maps cannot be indexed using index positions
    public String toString(){
        StringBuilder stringToReturn = new StringBuilder();
        for (Map.Entry<String,CustomerAccount> i: this.accounts.entrySet()){
            String entryRegNum = i.getKey();
            CustomerAccount entryAccount = i.getValue();
            stringToReturn.append(String.format(
                    "Details for %s: %s\n",entryRegNum,entryAccount));
        }
        return "Money made: " + this.moneyMade + "\n" + stringToReturn;
    }

    public static void main(String[] args){
        HashMap<String,CustomerAccount> accounts;
        accounts = new HashMap<>(10);

        //Test data: Van,HQ09WIJ,Jose,Phelps,Ford,700,3000,NONE
        CustomerAccount test1Customer;
        Vehicle test1Vehicle;

        //Test data: Van,BQ58VCQ,Alden,Fraga,Ford,450,2000,NONE
        CustomerAccount test2Customer;
        Vehicle test2Vehicle;

        //Test data: Car,VS56SYX,Brad,Whistler,Vauxhall,7,9000,NONE
        CustomerAccount test3Customer;
        Vehicle test3Vehicle;

        //Test data: Truck,ZD04UKP,Leona,Burk,Leyland,2,1100,NONE
        CustomerAccount test4Customer;
        Vehicle test4Vehicle;

        test1Vehicle = new Van("HQ09WIJ","Ford",700);
        test1Customer = new CustomerAccount("Jose","Phelps",3000,test1Vehicle);
        test2Vehicle = new Van("BQ58VCQ","Ford",450);
        test2Customer = new CustomerAccount("Alden","Fraga",2000,test2Vehicle);
        test3Vehicle = new Car("VS56SYX","Vauxhall",7);
        test3Customer = new CustomerAccount("Brad","Whistler",9000,test3Vehicle);
        test4Vehicle = new Truck("ZD04UKP","Leyland",2);
        test4Customer = new CustomerAccount("Leona","Burk",1100,test4Vehicle);
        TollRoad test1;
        test1 = new TollRoad(accounts,0);
        test1.addCustomer(test1Customer);
        test1.addCustomer(test2Customer);
        test1.addCustomer(test3Customer);
        test1.addCustomer(test4Customer);
        System.out.println("All accounts: " + test1.getAccounts());
        System.out.println("Money made: " + test1.getMoneyMade());
        System.out.println(test1);

        //Successful find customer

        try{
            test1.findCustomer("VS56SYX");
            System.out.println("\nFinding successful");
        } catch(CustomerNotFoundException e){
            System.out.println("Registration number could not be found");
        }

        //Failed find customer

        try{
            test1.findCustomer("Lorem ipsum");
            System.out.println("This shouldn't be successful");
        } catch(CustomerNotFoundException e){
            System.out.println("\nRegistration number could not be found, exception successful");
        }

        //Successful charge customer

        try{
            test1.chargeCustomer("HQ09WIJ");
            System.out.println("\nCharging successful");
            System.out.println("Money made is now " + test1.getMoneyMade());
        } catch(CustomerNotFoundException e){
            System.out.println("Registration number could not be found");
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient Balance");
        }

        //Failed charge customer, customer not found

        try{
            test1.chargeCustomer("Lorem ipsum");
            System.out.println("This shouldn't be successful");
        } catch(CustomerNotFoundException e){
            System.out.println("\nRegistration number could not be found, exception successful");
        } catch(InsufficientAccountBalanceException e){
            System.out.println("Insufficient Balance");
        }


        //Failed charge customer, insufficient balance

        try{
            test1.chargeCustomer("ZD04UKP");
            System.out.println("This shouldn't be successful");
        } catch(CustomerNotFoundException e){
            System.out.println("Registration number could not be found");
        } catch(InsufficientAccountBalanceException e){
            System.out.println("\nInsufficient Balance, exception successful");
        }

    }
}
