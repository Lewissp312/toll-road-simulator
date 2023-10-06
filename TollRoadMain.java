//This is the main class, where the customer details are read from the customerData file and placed into a hashmap.
//The transactions are read from the transactions.txt file and are processed

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class TollRoadMain {
    private HashMap<String,CustomerAccount> accounts;
    private ArrayList<ArrayList<String>> transactions;
    private TollRoad road;
    private ArrayList<String> entriesFromFile;
    private ArrayList<String> entryFromList;
    private Scanner fileScanner;
    private Scanner entryScanner;
    private Vehicle tempVehicle;
    private CustomerAccount tempCustomer;
    private File accountsFile;
    private File transactionsFile;

    public TollRoadMain(){
        this.accounts = new HashMap<>(50);
        this.transactions = new ArrayList<>(97);
        this.entriesFromFile = new ArrayList<>(50);

    }

    public TollRoad initialiseTollRoadFromFile(){
        try{
            //Adding customer accounts to a list
            this.accountsFile = new File("customerData.txt");
            this.fileScanner = new Scanner(this.accountsFile);
            this.fileScanner.useDelimiter("#");
            do{
                this.entriesFromFile.add(this.fileScanner.next());
            }while(this.fileScanner.hasNext());

            //Adding the details of a specific customer account to a list
            for (int i=0;i<this.entriesFromFile.size();i++){
                this.entryFromList = new ArrayList<>(8);
                this.entryScanner = new Scanner(this.entriesFromFile.get(i));
                this.entryScanner.useDelimiter(",");
                do{
                    this.entryFromList.add(this.entryScanner.next());
                }while(this.entryScanner.hasNext());

                if(this.entryFromList.get(0).equals("Car")){
                    this.tempVehicle = new Car(
                            this.entryFromList.get(1),
                            this.entryFromList.get(4),
                            Integer.parseInt(this.entryFromList.get(5)));

                } else if(this.entryFromList.get(0).equals("Van")){
                    this.tempVehicle = new Van(
                            this.entryFromList.get(1),
                            this.entryFromList.get(4),
                            Integer.parseInt(this.entryFromList.get(5)));

                } else{
                    this.tempVehicle = new Truck(
                            this.entryFromList.get(1),
                            this.entryFromList.get(4),
                            Integer.parseInt(this.entryFromList.get(5)));
                }
                this.tempCustomer = new CustomerAccount(
                        this.entryFromList.get(2),
                        this.entryFromList.get(3),
                        Integer.parseInt(this.entryFromList.get(6)),
                        this.tempVehicle);

                if (this.entryFromList.get(7).equals("STAFF")){
                    this.tempCustomer.activateStaffDiscount();
                } else if(this.entryFromList.get(7).equals("FRIENDS_AND_FAMILY")){
                    this.tempCustomer.activateFriendsAndFamilyDiscount();
                }
                this.accounts.put(this.entryFromList.get(1),this.tempCustomer);
            }
        } catch(IOException e){
            System.out.println("File error");
        }
        this.road = new TollRoad(this.accounts,0);
        return this.road;
    }

    public void simulateFromFile(TollRoad road){
        this.entriesFromFile = new ArrayList<>(97);
        try{
            //Same process as the customer accounts. The transactions are split into lists and then split again
            this.transactionsFile = new File("transactions.txt");
            this.fileScanner = new Scanner(this.transactionsFile);
            //The below method was obtained from:
            //https://stackoverflow.com/questions/3853726/java-regular-expressions-and-dollar-sign
            //This needs to be used as $ is recognised as a variable placeholder, and escape characters cannot be used in delimiters
            String dollarSign = java.util.regex.Pattern.quote("$");
            this.fileScanner.useDelimiter(dollarSign);
            do{
                this.entriesFromFile.add(this.fileScanner.next());
            } while(this.fileScanner.hasNext());

            for (int i=0; i<this.entriesFromFile.size();i++){
                this.entryFromList = new ArrayList<>(3);
                this.entryScanner = new Scanner(this.entriesFromFile.get(i));
                this.entryScanner.useDelimiter(",");
                do{
                    this.entryFromList.add(this.entryScanner.next());
                }while(this.entryScanner.hasNext());
                this.transactions.add(this.entryFromList);
            }
        } catch (IOException e){
            System.out.println("File error");
        }

        String command;
        String regNum;
        int money;
        for (int i=0;i<this.transactions.size();i++){
            command=this.transactions.get(i).get(0);
            regNum=this.transactions.get(i).get(1);
            if (command.equals("addFunds")){
                money=Integer.parseInt(this.transactions.get(i).get(2));
                try{
                    road.findCustomer(regNum).addFunds(money);
                    System.out.printf(
                            "%s: %s added successfully\n",regNum,money);
                } catch (CustomerNotFoundException e){
                    System.out.printf(
                            "%s: addFunds failed. CustomerAccount does not exist\n",regNum);
                }
            } else{
                try{
                    road.chargeCustomer(regNum);
                    System.out.printf(
                            "%s: Trip completed successfully\n",regNum);
                } catch(CustomerNotFoundException e){
                    System.out.printf(
                            "%s: makeTrip failed. CustomerAccount does not exist\n",regNum);
                } catch(InsufficientAccountBalanceException e){
                    System.out.printf(
                            "%s: makeTrip failed. Insufficient funds\n",regNum);
                }
            }
        }
    }

    public static void main(String[] args){
        TollRoadMain test1;
        test1 = new TollRoadMain();
        test1.simulateFromFile(test1.initialiseTollRoadFromFile());
        System.out.printf(
                "In total, %s was made from this toll road\n",test1.road.getMoneyMade());
    }
}
