/*
 * This file is the RentalManager class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */
package model;

import java.util.HashMap;
import java.util.Map;

import model.vehicles.AbstractVehicles;
import model.vehicles.BiCycle;
import model.vehicles.Car;
import model.vehicles.MotorBike;

/**
 * RentalManager class that creates the individual vehicles object
 * and places them into a hashmap, myVehicleList. 
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class RentalManager {
    
    /**
     * Creates a empty hashmap to store the vehicles object.
     */
    private final Map<Integer, AbstractVehicles> myVehicleList;
    
    /**
     * Creates a empty hashmap to store the bill object.
     */
    private Map<Integer, Bill> myBills;
    
    /**
     * A reference to the Registration object where the registration and login
     * process occurs.
     */
    private Registration myRegistration;
    
    /**
     * Constructor method that initializes the myRegistration and
     * places the inventory of the vehicles into myVehicleList by calling
     * the generateInventory() method. Initializes myBills to a new
     * HashMap object.
     * 
     * @throws NullPointerException if theRegistration is null.
     * 
     * @param theRegistration registration object.
     */
    public RentalManager(final Registration theRegistration) {
        myRegistration = theRegistration;
        
        myVehicleList = generateInventory();
        myBills = new HashMap<Integer, Bill>();
        
        if (theRegistration == null) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * generateInventory() method creates the objects of the vehicles,
     * then stores them into the myVehicleList hashmap.
     * 
     * @return the vehicle objects that will be in the HashMap.
     */
    public Map<Integer, AbstractVehicles> generateInventory() {
        int idNum = 1;
        
        final Map<Integer, AbstractVehicles> items = new HashMap<Integer, AbstractVehicles>();
        
        final Car carOne = new Car(idNum++, "V100", "Fiat");
        final Car carTwo = new Car(idNum++, "V101", "Outback", true, true, false);
        final Car carThree = new Car(idNum++, "V102", "BMW", true, true, true);
        
        final MotorBike bikeOne = new MotorBike(idNum++, "B100", "Bike1");
        final MotorBike bikeTwo = new MotorBike(idNum++, "B101", "Bike2", true);
       
        final BiCycle biCycleOne = new BiCycle(idNum++, "C100", "Roadies", "Road");
        final BiCycle biCycleTwo = new BiCycle(idNum++, "C101", "Cruiser", "Cruiser");
        final BiCycle biCycleThree = new BiCycle(idNum++, "C102", "Mountain", "Mountain");
        
        idNum = 0;
        items.put(idNum++, carOne);
        items.put(idNum++, carTwo);
        items.put(idNum++, carThree);
        items.put(idNum++, bikeOne);
        items.put(idNum++, bikeTwo);
        items.put(idNum++, biCycleOne);
        items.put(idNum++, biCycleTwo);
        items.put(idNum++, biCycleThree);
        
        return items;
        
    }
    
    /**
     * Getter for the vehicles list.
     * 
     * @return the vehicles list.
     */
    public Map<Integer, AbstractVehicles> getMyVehicleList() {
        return this.myVehicleList;
    }
  
    /**
     * Method that checks if the vehicle exists in the myVehicleList inventory and 
     * checks if the vehicle is not already rented.
     * 
     * @param theVehicleID the ID number of the vehicle.
     * @return true if the vehicle is part of the inventory, false otherwise.
     */
    public boolean isRentable(final int theVehicleID) {
        if (myVehicleList.containsKey(theVehicleID) && myVehicleList.get(theVehicleID).getMyRentStatus()) { // Get rent status.
            return true;
        }
        
        return false;
    }
    
    /**
     * Method that checks if the vehicle is availabel for rent by calling the isRentable method and
     * checks if the username is a registered user. If both of these conditions are true,
     * then the vehicle rent status will be set to false, a Bill object will be created,
     * the computeAndPrintAmount from the Bill object will be called to display the bill,
     * and the Bill object will to added to the myBills hashmap.
     * 
     * @throws NullPointerException (implicitly) if theUserName is null.
     * @throws IllegalArgumentException (explicitly) if theUserName is a empty string,
     *         and the rest of the integers (theVehicleID, theNumDays, theBillID) are negative.
     * 
     * @param theVehicleID the ID number of the vehicle.
     * @param theUserName the name of the User.
     * @param theNumDays the number of days that the vehicle will be rented.
     * @param theBillID the unique ID number of the bill.
     * @return true if theVehicleID is rentable AND theUserName is a registered user, false otherwise.
     */
    public boolean rent(final int theVehicleID, final String theUserName, 
                        final int theNumDays, final int theBillID) {
        if (theUserName == null) {
            throw new IllegalArgumentException();
        }
        
        if (theVehicleID < 0 || theUserName.trim().isEmpty() || theNumDays < 0 || theBillID < 0) {
            throw new IllegalArgumentException();
        }
        
        boolean rentAble = false;
        int billNum = 0;
        
        
        if (isRentable(theVehicleID) && myRegistration.getMyUserList().containsKey(theUserName)) {
            myVehicleList.get(theVehicleID).setMyRentStatus(false);
            final Bill bill = new Bill(theBillID, myRegistration.getMyUserList().get(theUserName), myVehicleList.get(theVehicleID), theNumDays);
            bill.computeAndPrintAmount();
            myBills.put(billNum++, bill);
            rentAble = true;
        }
        
        return rentAble;
    }
    
    /**
     * Method that checks if the vehicle is available to be dropped off by checking 
     * if theVehicleID of the vehicle is valid and calls the isRentable method to check
     * if the vehicle is not available to rent. If both of the condition are true, then the vehicle
     * can be dropped off. 
     * 
     * @param theVehicleID the ID number of the vehicle.
     * @return true if theVehicleID is valid and is not rentable, false otherwise.
     */
    public boolean drop(final int theVehicleID) {
        if (myVehicleList.containsKey(theVehicleID) && !isRentable(theVehicleID)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * printOptions() method to prints out three different options for the user.
     * option 1 is rent, option 2 is drop-off, and option 3 is to exit. 
     * The method will keep running unless the user selects the third option or types down
     * "false" when asked if they want to continue.
     */
    public void printOptions() {
        final String divider = "***********************";
        final String contin = " Do you want to continue?";
        boolean cont = true;
        while (cont) {
            System.out.println("Enter 1 or 2 or 3 (1. Rent 2. Drop-off 3.Exit):");
            final int num = Registration.SCAN.nextInt();
        
            System.out.println("You entered option " + num);
            System.out.println("\n" + divider + divider);
            if (num == 1) {
                printOptionsOne();
                System.out.println(divider);
                System.out.print(contin);
                if (!Registration.SCAN.nextBoolean()) {
                    cont = false;
                }
            } else if (num == 2) {
                printOptionsTwo();
                System.out.println(divider);
                System.out.print(contin);
                if (!Registration.SCAN.nextBoolean()) {
                    cont = false;
                }
            } else if (num == 3) {
                cont = false;
            }
        }
    }
    
    /**
     * Utility method for printOptions() method. Option one will print out each
     * of the available vehicles to the user that can be rented. Then the myBillID
     * will be generated and will increment each time a vehicle is rented. Then,
     * the user will be prompted to type down one of the available vehicle ID, the user's
     * username, and the number of days that the vehicle will be rented. Afterward,
     * the rent method will be called to check and confirm all of the information
     * of the user inputed are correct and will return true if correct. If incorrect, the user
     * will be then prompted to re-input the information of the vehicleID, username,
     * and the number of days until the rent method returns true.
     */
    public void printOptionsOne() {
        System.out.println("List of available vehicles:");
        
        final RentalManager rm = new RentalManager(myRegistration);
        
        for (Integer key: rm.getMyVehicleList().keySet()) {
            if (myVehicleList.get(key).getMyRentStatus()) {
                System.out.println(rm.getMyVehicleList().get(key));
            }
        }
        final String divider = "**********************";
        
        String name = "";
        int vehID = 0;
        int numDays = 0;
        int billID = 0;
     
        boolean wrongVehicleID = true;
        boolean wrongUser = true;
    
        while (wrongVehicleID && wrongUser) {
            System.out.println(divider);
            System.out.println(" Enter Rental Details");
            System.out.println(divider);
            
            System.out.print("Enter Vehicle ID:");
            final int vehicleID = Registration.SCAN.nextInt() - 1;
            
            System.out.print("Enter User Name:");
            final String userName = Registration.SCAN.next();

            System.out.print("Enter NumDays to Rent:");
            final int numDay = Registration.SCAN.nextInt();
            
            if (!isRentable(vehicleID)) {
                System.out.println("Vehicle not rentable");
            } else if (!myRegistration.getMyUserList().containsKey(userName)) {
                System.out.println("User does not exists, enter different user name:");
            } else {
                wrongVehicleID = false;
                vehID = vehicleID;
                
                wrongUser = false;
                name = userName;
                
                numDays = numDay;
            }
        }
        
        System.out.println("\n" + divider);
        System.out.println(" Rental Bill Summary");
        System.out.println(divider);
        System.out.println("User Name:" + name);
            
        System.out.println("----Vehicle Information----");
        System.out.println("VehicleName " + myVehicleList.get(vehID).getMyName());
        System.out.println("VehicleID " + myVehicleList.get(vehID).getMyVehicleID());
        System.out.println("VehicleType " + myVehicleList.get(vehID).getMyVIN());
        System.out.println("VIN " + myVehicleList.get(vehID).getMyVIN());
        
        System.out.println("----Cost Information----");
        System.out.println("RentalPerDay:");
        
        rent(vehID, name, numDays, billID++);
        System.out.println("Rent Successfull");
            
    }
    
    /**
     * Utility method for printOptions() method. Option two will ask the user
     * to input the vehicle ID that they want to drop off. Then the method will
     * call the drop method to check if the vehicle is not rented and if the vehicle
     * is valid. If not, this method will keep running until the drop method returns
     * true.
     */
    public void printOptionsTwo() {
        final String divider = "*********************";
        
        boolean dropOff = true;
        System.out.println(divider);
        System.out.println(" Enter Drop-off Details");
        System.out.println(divider);
        
        while (dropOff) {
            System.out.print("Enter Drop-off Vehicle ID:");
            final int vehicleID = Registration.SCAN.nextInt() - 1;
            if (drop(vehicleID)) {
                System.out.println("Drop-off Successful");
                myVehicleList.get(vehicleID).setMyRentStatus(true);
                dropOff = false;
            } else if (isRentable(vehicleID)) {
                System.out.println("Vehicle is not rented already");
            } else {
                System.out.println("Vehicle does not exists");
            }
        }
    }
    
    /**
     * Getter for the registration object.
     * 
     * @return the registration object.
     */
    public Registration getMyRegistration() {
        return this.myRegistration;
    }
    
    /**
     * clearLists() method to clear myVehicleList hashmap and
     * myBills hashmap.
     */
    public void clearLists() {
        myVehicleList.clear();
        myBills.clear();
    }
}