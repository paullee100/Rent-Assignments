/*
 * This file is the MotorBike child class of the abstract Vehicle class.
 * 
 * TCSS 305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * MotorBike object that extends to the AbstractVehicles object and uses the field,
 * myVehicleID, myVIN, and myName, as well as creating a field myTouring  
 * to check if the MotorBike is a "touring" type.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class MotorBike extends AbstractVehicles {
    
    /**
     * The base cost of each MotorBike which is twice as much as the BASE_FARE.
     */
    private static final BigDecimal BIKE_FARE = BASE_FARE.add(BASE_FARE);
    
    /**
     * Touring status of the MotorBike.
     */
    private boolean myTouring;
    
    /**
     * Constructor method that uses the super() method to refer to the
     * AbstractVehicle constructor. Automatically assigns false to myTouring.
     * 
     * @param theVehicleID the unique vehicle ID of the MotorBike.
     * @param theVIN the vehicle identification number of the MotorBike.
     * @param theName the name of the MotorBike.
     * @param theRentStatus the rent status of the MotorBike.
     */
    public MotorBike(final int theVehicleID, final String theVIN, final String theName) {
        super(theVehicleID, theVIN, theName);
        this.myTouring = false;
    }
    
    /**
     * Constructor method that uses the super() method to refer to the
     * AbstractVehicle constructor. Sets myTouring to either true or false
     * based on theTouring.
     * 
     * @param theVehicleID the unique vehicle ID of the MotorBike.
     * @param theVIN the vehicle identification number of the MotorBike.
     * @param theName the name of the MotorBike.
     * @param theTouring if the MotorBike is touring or not.
     */
    public MotorBike(final int theVehicleID, final String theVIN, final String theName, 
                     final boolean theTouring) {
        super(theVehicleID, theVIN, theName);
        this.myTouring = theTouring;
    }
    
    /**
     * Getter for myTouring.
     * 
     * @return if the MotorBike is categorized as touring or not.
     */
    public boolean getMyTouring() {
        return this.myTouring;
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
     * calculateRentalAmount() calculates the rental amount and adds more
     * if the MotorBike is touring. A touring MotorBike cost $5 more then
     * the standard BIKE_FARE cost.
     * 
     * @return the calculated rental amount of the MotorBike.
     */
    public BigDecimal calculateRentalAmount() {
        final BigDecimal motorBikeCost;
        
        if (this.myTouring) {
            final BigDecimal touring = new BigDecimal("5.00");
            motorBikeCost = BIKE_FARE.add(touring);
        } else {
            motorBikeCost = BIKE_FARE;
        }
        
        return motorBikeCost;
    }
    
    /**
     * toString() method to print out the proper
     * String representation, for example:
     * MotorBike (ID:5, Name:Bike2, VIN:B101, CanRent?:true, IsTouring?:true).
     * This is only for one of the MotorBike object and will change between
     * MotorBike objects.
     * 
     * @return the string representation of the MotorBike object.
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
        sb.append(", IsTouring?:");
        sb.append(getMyTouring());
        sb.append(')');
        return sb.toString();
    }
    
    /**
     * Compares the MotorBike object with the other MotorBike object to see
     * if they equal or not.
     * 
     * @param theOtherObject the other MotorBike object.
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
        
        final MotorBike otherObject = (MotorBike) theOtherObject;
        return Boolean.compare(this.myTouring, otherObject.myTouring) == 0
               && Integer.compare(this.getMyVehicleID(), otherObject.getMyVehicleID()) == 0
               && this.getMyVIN().equals(otherObject.getMyVIN())
               && this.getMyName().equals(otherObject.getMyName())
               && Boolean.compare(this.getMyRentStatus(), otherObject.getMyRentStatus()) == 0;
    }
    
    /**
     * hashCode() method creates a hashcode using the fields from the parent object,
     * AbstractVehicles object, except for myRentalAmount and myTouring.
     * 
     * @return hashCode of myVehicleID, myVIN, myName, and myTouring.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myTouring);
    }
}