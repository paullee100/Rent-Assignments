/*
 * This file is the BiCycle child class of the abstract Vehicle class.
 * 
 * TCSS 305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * BiCycle object that extends to the AbstractVehicles object and uses the field,
 * myVehicleID, myVIN, and myName, as well as creating a myCycleType for 
 * the different cycle type of the BiCycle. The BiCycle also has
 * four child object for the four types of BiCycle 
 * (Road, Mountain, Cruiser, and Hybrid).
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class BiCycle extends AbstractVehicles {
    
    /**
     * The base cost for each BiCycle which is equal to the BASE_FARE.
     */
    private static final BigDecimal CYCLE_FARE = BASE_FARE;

    /**
     * The cycle type of the BiCycle.
     */
    private String myCycleType;
    
    /**
     * Constructor method that uses the super() method to refer to the
     * AbstractVehicle constructor.
     * 
     * @param theVehicleID the unique vehicle ID for the BiCycle.
     * @param theVIN the vehicle identification number of the BiCycle.
     * @param theName the name of the BiCycle.
     * @param theCycleType the BiCycle type.
     */
    public BiCycle(final int theVehicleID, final String theVIN, final String theName,
                   final String theCycleType) {
        super(theVehicleID, theVIN, theName);
        this.myCycleType = theCycleType;
    }
    
    /**
     * Getter for myCycleType.
     * 
     * @return the cycle type.
     */
    public String getMyCycleType() {
        return this.myCycleType;
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
     * calculateRentalAmount() method calculates the rental amount
     * and adds more based on the specific type of BiCycle. A road BiCycle
     * cost the same as CYCLE_FARE, mountain BiCycle cost 1% more then CYCLE_FARE, 
     * cruiser BiCycle cost 2% more then CYCLE_FARE, and hybrid BiCycle cost
     * 4% more then CYCLE_FARE.
     * 
     * @return the calculated rental amount of the BiCycle in BigDecimal.
     */
    public BigDecimal calculateRentalAmount() {
        final BigDecimal biCycleCost;
       
        if (myCycleType.compareTo("Mountain") == 0) {
            final BigDecimal mountain = new BigDecimal("1.01");
            biCycleCost = CYCLE_FARE.multiply(mountain);
        } else if (myCycleType.compareTo("Cruiser") == 0) {
            final BigDecimal cruiser = new BigDecimal("1.02");
            biCycleCost = CYCLE_FARE.multiply(cruiser);
        } else if (myCycleType.compareTo("Hybrid") == 0) {
            final BigDecimal hybrid = new BigDecimal("1.04");
            biCycleCost = CYCLE_FARE.multiply(hybrid);
        } else {
            biCycleCost = CYCLE_FARE;
        }
        
        return biCycleCost;
    }
    
    /**
     * toString() method to print out the proper
     * string representation, for example:
     * BiCycle (ID:6, Name:Roadies, VIN:C100,CanRent?:true,CycleType:Road).
     * This is only for one of the BiCycle object and will change between
     * BiCycle objects.
     * 
     * @return the string representation of the BiCycle object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BiCycle (ID:");
        sb.append(this.getMyVehicleID());
        sb.append(", Name:");
        sb.append(this.getMyName());
        sb.append(", VIN:");
        sb.append(this.getMyVIN());
        sb.append(", CanRent?:");
        sb.append(this.getMyRentStatus());
        sb.append(", CycleType:");
        sb.append(this.getMyCycleType());
        sb.append(')');
        return sb.toString();
    }
    
    /**
     * Compares the BiCycle object with the other BiCycle object to see
     * if they equal or not.
     * 
     * @param theOtherObject the other BiCycle object.
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
        
        final BiCycle otherObject = (BiCycle) theOtherObject;
        return this.myCycleType.compareTo(otherObject.myCycleType) == 0
               && Integer.compare(this.getMyVehicleID(), otherObject.getMyVehicleID()) == 0
               && this.getMyVIN().compareTo(otherObject.getMyVIN()) == 0
               && this.getMyName().compareTo(otherObject.getMyName()) == 0
               && Boolean.compare(this.getMyRentStatus(), otherObject.getMyRentStatus()) == 0;
    }
    
    /**
     * hashCode() method creates a hashcode using the fields from the parent object,
     * AbstractVehicles object, except for myRentalAmount and myCycleType.
     * 
     * @return hashCode of myVehicleID, myVIN, myName, and myCycleType.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myCycleType);
    }
}
