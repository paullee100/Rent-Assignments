/*
 * This file is the AbstractVehicle abstract class for the Vehicle rental system.
 * 
 * TCSS 305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;

/**
 * Abstract Vehicle object contains three fields, myVehicleID, myVIN, and myName
 * that all of the child object will contain. There will be three child object,
 * BiCycle, MotorBike, and Car. The BASE_FARE is set up in this class and will be
 * in BigDecimal value in $. myRentalAmount, also in BigDecimal, is created as an
 * abstract method for the child object to use.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public abstract class AbstractVehicles {
    
    /**
     * The base cost, $10, for each of the vehicle, unless stated otherwise in
     * the specific vehicle object.
     */
    public static final BigDecimal BASE_FARE = new BigDecimal("10.00");
    
    /**
     * Unique ID for each vehicle that is auto generated
     * by rental manager during inventory creation. Starts
     * from 1.
     */
    private int myVehicleID;
    
    /**
     * Vehicle Identification Number for each unique vehicle.
     */
    private String myVIN;
    
    /**
     * Name of the vehicle.
     */
    private String myName;
    
    /**
     * Rent status of the vehicle.
     */
    private boolean myRentStatus = true;

    /**
     * Constructor method to assign each fields to the parameter.
     * 
     * @param theVehicleID the unique vehicle ID of each vehicle.
     * @param theVIN the vehicle identification number of the vehicle.
     * @param theName the name of the vehicle.
     */
    public AbstractVehicles(final int theVehicleID, final String theVIN, final String theName) {
        this.myVehicleID = theVehicleID;
        this.myVIN = theVIN;
        this.myName = theName;
    }
    
    /**
     * myRentalAmount() abstract method to set the rental amount based on the specific
     * vehicle (Car, MotorBike, or BiCycle).
     * 
     * @return the rental amount in BigDecimal.
     */
    public abstract BigDecimal myRentalAmount();

    
    /**
     * Getter for the ID of the vehicle.
     * 
     * @return the vehicle ID.
     */
    public int getMyVehicleID() {
        return this.myVehicleID;
    }
    
    /**
     * Getter for the vehicle identification number.
     * 
     * @return the VIN of the vehicle.
     */
    public String getMyVIN() {
        return this.myVIN;
    }
    
    /**
     * Getter for the name of the vehicle.
     * 
     * @return the name of the vehicle.
     */
    public String getMyName() {
        return this.myName;
    }
    
    /**
     * Getter for the rent status of the vehicle.
     * 
     * @return the rent status of the vehicle.
     */
    public boolean getMyRentStatus() {
        return this.myRentStatus;
    }
    
    /**
     * Setter for the rent status of the vehicle.
     * 
     * @param theRentStatus the rent status of the vehicle.
     */
    public void setMyRentStatus(final boolean theRentStatus) {
        this.myRentStatus = theRentStatus;
    }
}
