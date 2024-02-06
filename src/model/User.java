/*
 * This file is the User class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */
package model;

import java.util.Objects;

/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class 
 * @throws NullPointerException if required parameters are null.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 */
public final class User {
    /**
     * Username of the user.
     */
    private String myName;
    
    /**
     * Password of the user.
     */
    private String myPassword;
    
    /**
     * VIP status of the user.
     */
    private boolean myVIPStatus;
    
    /**
     * Constructor for the variable to equal to the parameter.
     * 
     * @throws NullPointException (implicitly) if name or password is null.
     * @throws IllegalArgumentException (explicitly) if name or password is blank.
     * 
     * @param theName username to set the variable myName.
     * @param thePassword password to set the variable myPassword.
     */
    public User(final String theName, final String thePassword) {
        this.myName = theName;
        this.myPassword = thePassword;
        this.myVIPStatus = false;
        
        if (theName == null || thePassword == null) {
            throw new IllegalArgumentException();
        }
        
        if (theName.trim().isEmpty() || thePassword.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Constructor
     * @throws NullPointerException (implicitly) if the username or password is null
     * @throws IllegalArgumentException (explicitly) if the username or password is blank
     * 
     * @param theName username to set the variable theName.
     * @param thePassword password to set the variable thePassword.
     * @param theVIPStatus VIP status to set the variable theVIPStatus.
     */
    public User(final String theName, final String thePassword, final boolean theVIPStatus) {
        this.myName = theName;
        this.myPassword = thePassword;
        this.myVIPStatus = theVIPStatus;
        
        if (this.myName == null || this.myPassword == null) {
            throw new IllegalArgumentException();
        }
        
        if (this.myName.trim().isEmpty() || this.myPassword.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Getter for myName.
     * 
     * @return myName the name of the user.
     */
    public String getMyName() {
        return this.myName;
    }
    
    /**
     * Getter for myPassword.
     * 
     * @return myPassword the password of the user.
     */
    public String getMyPassword() {
        return this.myPassword;
    }
    
    /**
     * Getter for myVIPStatus.
     * 
     * @return the VIP status of the user.
     */
    public boolean getMyVIPStatus() {
        return this.myVIPStatus;
    }
    
    /**
     * Method to make the statement readable by the user 
     * and to properly set the format, which is
     * User(name, password, VIPStatus).
     * 
     * @return the string representation of the User object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" (");
        sb.append(String.format(this.myName));
        sb.append(", ");
        sb.append(String.format(this.myPassword));
        sb.append(", ");
        sb.append(this.myVIPStatus);
        sb.append(')');
        return sb.toString();
    }
    
    /**
     * Method compares User with the other User object to see if
     * they equal or not, using myName, myPassword, and
     * myVIPStatus to compare.
     * 
     * @param theOtherObject the other User object
     * @return if this object equals to the parameter theOtherObject 
     *         (true or false)
     */
    @Override
    public boolean equals(final Object theOtherObject) {
        boolean results = true;
        
        if (this == theOtherObject) {
            results = true;
        } else if (theOtherObject == null) {
            results = false;
        } else if (getClass() != theOtherObject.getClass()) {
            results = false;
        } else {
            final User otherObject = (User) theOtherObject;
            results = this.myName.equals(otherObject.myName)
                      && this.myPassword.equals(otherObject.myPassword)
                      && Boolean.compare(this.myVIPStatus, otherObject.myVIPStatus) == 0;
        }
        
        return results;
    }
    
    /**
     * Method creates a hashcode using myUsername, myPassword, and
     * myVIPStatus.
     * 
     * @return hashCode integer of myUsername, myPassword, and myVIPStatus.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
}
