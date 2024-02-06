/*
 * This file is the Car child class of the abstract Vehicle class
 * 
 * TCSS 305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Car object that extends to the AbstractVehicles object and uses the field,
 * myVehicleID, myVIN, and myName, as well as creating three fields myLuxury, 
 * myNavigation, and myAssistance that the Car can have.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class Car extends AbstractVehicles {
    
    /**
     * Base cost of each car, which is thrice as much as the BASE_FARE.
     */
    private static final BigDecimal CAR_FARE = BASE_FARE.add(BASE_FARE).add(BASE_FARE);
    
    /**
     * Luxury status of the car.
     */
    private boolean myLuxury;
    
    /**
     * If the car has navigation.
     */
    private boolean myNavigation;
    
    /**
     * If the car has assistance.
     */
    private boolean myAssistance;
    
    /**
     * Constructor method that uses the super() method to refer to the
     * AbstractVehicle constructor. Automatically assigns false to myLuxury,
     * myNavigation, and myAssistance.
     * 
     * @param theVehicleID the unique vehicle ID for the Car.
     * @param theVIN the vehicle identification number of the Car.
     * @param theName the name of the Car.
     */
    public Car(final int theVehicleID, final String theVIN, final String theName) {
        super(theVehicleID, theVIN, theName);
        this.myLuxury = false;
        this.myNavigation = false;
        this.myAssistance = false;
    }
    
    /**
     * Constructor method that uses the super() method to refer to the
     * AbstractVehicle constructor. Sets myLuxury to theLuxury, 
     * myNavigation to theNavigation, and myAssistance to the Assistance.
     * 
     * @param theVehicleID the unique vehicle ID for the Car.
     * @param theVIN the vehicle identification number of the Car.
     * @param theName the name of the Car.
     * @param theLuxury if the Car is luxury or not.
     * @param theNavigation if the Car has navigation or not.
     * @param theAssistance if the Car has assistance or not.
     */
    public Car(final int theVehicleID, final String theVIN, final String theName,
               final boolean theLuxury, final boolean theNavigation, final boolean theAssistance) {
        super(theVehicleID, theVIN, theName);
        this.myLuxury = theLuxury;
        this.myNavigation = theNavigation;
        this.myAssistance = theAssistance;
    }
    
    /**
     * Getter for myLuxury.
     * 
     * @return if the car is luxury or not.
     */
    public boolean getMyLuxury() {
        return this.myLuxury;
    }
    
    /**
     * Getter for myNavigation.
     * 
     * @return if the car has navigation or not.
     */
    public boolean getMyNavigation() {
        return this.myNavigation;
    }
    
    /**
     * Getter for myAssistance.
     * 
     * @return if the car has assistance or not.
     */
    public boolean getMyAssistance() {
        return this.myAssistance;
    }
    
    /**
     * myRentalAmount method returns the rental amount after calculation
     * in the calculateRentalAmount() method.
     * 
     * @return the rentalAmount in BigDecimal.
     */
    public BigDecimal myRentalAmount() {
        return calculateRentalAmount();
    }
    
    /**
     * calculateRentalAmount() method calculates the rental amount and 
     * adds more if the Car is luxury, has navigation, and/or has assistance.
     * $10 is added if the Car is luxury, $1 is added if the Car has
     * navigation, and $2 is added if the Car has assistance.
     * 
     * @return the calculated rental amount of the Car.
     */
    public BigDecimal calculateRentalAmount() {
        final BigDecimal carCost = CAR_FARE;
        BigDecimal luxuryCost = new BigDecimal("0");
        BigDecimal navigationCost = new BigDecimal("0");
        BigDecimal drivingAssistanceCost = new BigDecimal("0");

        if (this.myLuxury) {
            luxuryCost = new BigDecimal("10.00");
        } 
        
        if (this.myNavigation) {
            navigationCost = new BigDecimal("1.00");
        } 
        
        if (this.myAssistance) {
            drivingAssistanceCost = new BigDecimal("2.00");
        }
        
        return carCost.add(luxuryCost).add(navigationCost).add(drivingAssistanceCost);
    }
    
    /**
     * toString() to bring out the proper
     * String representation, for example:
     * Car (ID:3, Name:BMW, VIN:V102, CanRent?:true, IsLuxury?:true, 
     *      HasNavigation?:true, HasAssistance?:true).
     * This is only for one of the Car object and will change between
     * Car objects.
     * 
     * @return the string representation of the Car object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" (ID:");
        sb.append(this.getMyVehicleID());
        sb.append(", Name:");
        sb.append(this.getMyName());
        sb.append(", VIN:");
        sb.append(this.getMyVIN());
        sb.append(", CanRent?:");
        sb.append(this.getMyRentStatus());
        sb.append(", isLuxury?:");
        sb.append(this.myLuxury);
        sb.append(", HasNavigation?:");
        sb.append(this.myNavigation);
        sb.append(", HasAssistance?:");
        sb.append(this.myAssistance);
        sb.append(')');
        return sb.toString();
    }
    
    /**
     * Compares the Car object with the other Car object to see
     * if they equal or not.
     * 
     * @param theOtherObject the other Car object.
     * @return true if this object is equal to the parameter Object theOtherObject, false otherwise.
     */
    @Override
    public boolean equals(final Object theOtherObject) {
        if (this == theOtherObject) {
            return true;
        }
        if (theOtherObject == null) {
            return false;
        }
        if (getClass() != theOtherObject.getClass()) {
            return false;
        }
        final Car otherObject = (Car) theOtherObject;
        return Boolean.compare(this.myLuxury, otherObject.myLuxury) == 0
               && Boolean.compare(this.myNavigation, otherObject.myNavigation) == 0
               && Boolean.compare(this.myAssistance, otherObject.myAssistance) == 0
               && Integer.compare(this.getMyVehicleID(), otherObject.getMyVehicleID()) == 0
               && this.getMyVIN().compareTo(otherObject.getMyVIN()) == 0
               && this.getMyName().compareTo(otherObject.getMyName()) == 0
               && Boolean.compare(this.getMyRentStatus(), otherObject.getMyRentStatus()) == 0;
    }
    
    /**
     * hashCode() method creates a hashcode using the fields from the parent object,
     * AbstactVehicles object, except for myRentalAmount and myLuxury, myNavigation, and
     * myAssistance.
     * 
     * @return hashCode of myVehicleID, myVIN, myName, myLuxury, myNavigation, 
     *         and myAssistance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myLuxury, myNavigation, myAssistance);
    }
}