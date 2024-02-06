/*
 * This file is the Bill class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.AbstractVehicles;

/**
 * Bill object contains five fields, myBillID, myPrimaryUser, myVehicle, myNumDays,
 * and myBillAmount. The Bill object is primarily used to calculate the total rental
 * bill of the vehicle, as well as printing out the bill.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class Bill {
    /**
     * Unique integer value.
     */
    private int myBillID;
    
    /**
     * User object.
     */
    private User myPrimaryUser;
    
    /**
     * Vehicle object.
     */
    private AbstractVehicles myVehicle;
    
    /**
     * Integer representing the number of days the vehicle is rented.
     */
    private int myNumDays;
    
    /**
     * Total bill amount in BigDecimal Value.
     */
    private BigDecimal myBillAmount;
    
    /**
     * Constructor method that initializes the int myBillID to int theBillID, 
     * User myPrimaryUser to User thePrimaryUser, 
     * AbstractVehicles myVehicle to AbstractVehicles theVehicle, and
     * int myNumDays to int theNumDays. Sets BigDecimal myBillAmount to 0.
     * 
     * @param theBillID the unique ID of the bill.
     * @param thePrimaryUser the user that will be using the rental.
     * @param theVehicle the type of vehicle.
     * @param theNumDays the number of days that the vehicle will be rented.
     */
    public Bill(final int theBillID, final User thePrimaryUser, 
                final AbstractVehicles theVehicle, final int theNumDays) {
        this.myBillID = theBillID;
        this.myPrimaryUser = thePrimaryUser;
        this.myVehicle = theVehicle;
        this.myNumDays = theNumDays;
        this.myBillAmount = new BigDecimal("0");
    }

    /**
     * Gets the unique bill id.
     * @return the bill id
     */
    public int getBillID() {
        return this.myBillID;
    }
    
    /**
     * computeAndPrintAmount() method computes and prints out the myBillAmount in U.S.
     * currency. There are four variables that will be used to calculate the total rental.
     * First, the rental amount is calculated on myNumDays multiplied by the cost of the vehicle
     * rental per day. 
     * Second, insurance is added, which is 1% of the rental amount.
     * Third, if the VIP status of the user is true, then they will get a discount of 1% of
     * the rental amount.
     * Fourth, tax is added, which is 10% of the total calculation (after the insurance and 
     * VIP discount are applied).
     * Each step will be printed to display to the user.
     */
    public void computeAndPrintAmount() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final BigDecimal costPerDay = myVehicle.myRentalAmount();
        System.out.println("Cost per day: " + nf.format(costPerDay));
        
        final BigDecimal rentalAmount = new BigDecimal(this.myNumDays).multiply(costPerDay);
        System.out.println("No.of Rental days: " + this.myNumDays);
        System.out.println("Total Amount: " + nf.format(rentalAmount));
        
        final BigDecimal insurance = new BigDecimal("0.01");
        final BigDecimal insuranceCost = rentalAmount.multiply(insurance);
        System.out.println("Insurance: " + nf.format(insuranceCost));
        
        BigDecimal noTaxRentalAmount = rentalAmount.add(insuranceCost);
        
        if (myPrimaryUser.getMyVIPStatus()) {
            final BigDecimal vipStat = new BigDecimal("0.01");
            final BigDecimal vipDiscount = rentalAmount.multiply(vipStat);
            System.out.println("VIPDiscount: -" + nf.format(vipDiscount));
            noTaxRentalAmount = rentalAmount.subtract(vipDiscount).add(insuranceCost);
        }
        
        final BigDecimal tax = new BigDecimal("0.1");
        final BigDecimal taxCost = rentalAmount.multiply(tax);
        System.out.println("Tax: " + nf.format(taxCost));
        
        myBillAmount = noTaxRentalAmount.add(taxCost);
        System.out.println("Total Rent: " + nf.format(myBillAmount));
    }
}
