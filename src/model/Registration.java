
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Map;
import java.util.Scanner;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class
 * @throws NullPointerException if required parameters are null.
 * 
 * @author Athirai
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 */

public class Registration {
    
    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";
    
    /**
     * 
     */
    public static final Scanner SCAN = new Scanner(System.in);

    /**
     * The registered user list for signin.
     */
    private final Map<String, User> myUserList;

    /**
     * Constructs a sign-in/registration system.
     * 
     * 
     */
    public Registration() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {
        return myUserList;
    }
    
    /**
     * display sign-in or registration options.
     * 
     * @return true if login is successful, false otherwise.
     */
    public boolean printSignin() {
        
        // ------------Fill in--------------------//
        boolean loginComp = false;
        System.out.print("Enter 1 or 2 (1. New Registration 2. Login): ");
        final int num = SCAN.nextInt();
        
        System.out.println("You entered option " + num);
        
        System.out.println("\n**********************");
        System.out.println(" Enter Details");
        System.out.println("**********************");
        
        if (num == 1) {
            
            System.out.print("User Name: ");
            boolean dupeName = true;
            String name = SCAN.next();
            
            while (dupeName) {
                if (myUserList.containsKey(name)) {
                    System.out.print("User already exists, enter different user name: ");
                    final String name2 = SCAN.next();
                    name = name2;
                } else {
                    dupeName = false;
                }
            }
            
            System.out.print("Pass Word: ");
            final String password = SCAN.next();
            
            System.out.print("isVIP(true/false): ");
            final boolean vip = SCAN.nextBoolean();
            
            final User user = new User(name, password, vip);
            if (register(user)) {
                System.out.print("Registration Successful");
            }
        } else if (num == 2) {
            boolean wrongLoginInfo = true;
            
            while (wrongLoginInfo) {
                System.out.print("Username: ");
                final String name = SCAN.next();
            
                System.out.print("Password: ");
                final String password = SCAN.next();
                
                final User user = new User(name, password);
            
                if (login(user.getMyName(), user.getMyPassword())) {
                    System.out.println("Login Successful");
                    wrongLoginInfo = false;
                    loginComp = true;
                } else {
                    System.out.println("Wrong Credentials");
                    System.out.println();
                }
            }
        }
        return loginComp;
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @throws NullPointException (implicitly) if username or password is null
     * @throws IllegalArgumentException (explicitly) if username or password is blank
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for signin
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {

        // ------------Fill in--------------------//
        if (myUserList.containsKey(theUsername)) {
            if (myUserList.get(theUsername).getMyPassword().equals(thePassword)) {
                return true;
            }
        }
        
        if (theUsername == null || thePassword == null) {
            throw new IllegalArgumentException();
        }
    
        if (theUsername.isEmpty() || thePassword.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        return false;
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @throws NullPointException (implicitly) if user is null
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successfull
     */
    public boolean register(final User theUser) {

        // ------------Fill in--------------------//
        if (theUser == null) {
            throw new IllegalArgumentException();
        }
        
        FileLoader.writeUserToFile(USERFILE_NAME, theUser);
        myUserList.put(USERFILE_NAME, theUser);
        
        return true;
    }

    /**
     * Empties the user list.
     */
    public void clear() {
        // ------------Fill in--------------------//
        myUserList.clear();
    }

    @Override
    /**
     * String representation of the object
     * 
     * @return String representation of the Registration object.
     */
    public String toString() {

        // ------------Fill in--------------------//
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        sb.append(myUserList.toString());
        return sb.toString();
    }

}
