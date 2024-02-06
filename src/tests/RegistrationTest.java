/**
 * TCSS 305
 */
package tests;

import static org.junit.Assert.*;

import model.Registration;
import model.User;
import utility.FileLoader;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Registration class to test the methods and constructor
 * and to make sure that they are working correctly. Coverage of the
 * Registration is greater then 40% and does not cover the 
 * printSignin() method.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class RegistrationTest {

    /**
     * Class object Registration to be used to test the Registration class.
     */
    private Registration myRegistration1; 
    
    /**
     * Class object User to be used to be used to test the Registration class,
     * where a method might call to the User class.
     */
    private User myUser1;
    
    /**
     * A method to initialize the Registration object and the User object
     * to test the methods listed in Registration class.
     * @Before ensures that this method goes first before
     * going through the other test method.
     */
    @Before
    public void setUp() {
        myRegistration1 = new Registration();
        myUser1 = new User("name", "pass");
    }
    
    /**
     * Test method for {@link model.Registration#Registration()}.
     * Tests to make sure that the Object registration
     * is not null and the string value is equal to
     * the Map.
     */
    @Test
    public void testRegistration() {
        assertNotNull("Object is null", myRegistration1);
        assertEquals("Default values are not correct!", myRegistration1.getMyUserList(), FileLoader.readItemsFromFile("./resources/registeredusers.txt"));
    }

    /**
     * Test method for {@link model.Registration#getMyUserList()}.
     * Tests to make sure that the object registration is not null
     * and properly returns the Map myUserList.
     */
    @Test
    public void testGetMyUserList() {
        assertNotNull("Object is null", myRegistration1);
        assertEquals("Default values are not correct", FileLoader.readItemsFromFile("./resources/registeredusers.txt"), myRegistration1.getMyUserList());
    }

    /**
     * Test method for {@link model.Registration#login(String, String)}.
     * Tests to make sure that the object is not null and
     * that the input username is within the Map.
     */
    @Test
    public void testLogin() {
        assertNotNull("Object is null!", myRegistration1.login(myUser1.getMyName(), myUser1.getMyPassword()));
        assertTrue("myUserList key does not contains username!", myRegistration1.getMyUserList().containsKey(myUser1.getMyName()));
        
        final User user2 = new User("WrongName", "WrongPass");
        assertFalse("myUserList key contains username", myRegistration1.login(user2.getMyName(), user2.getMyPassword()));
    }

    /**
     * Test method for {@link model.Registration#login(String, String)}.
     * Tests if NullPointerException works.
     */
    @Test(expected = NullPointerException.class)
    public void testLoginNull() {
        myRegistration1.login(null, null);
    }
    
    /**
     * Test method for {@link model.Registration#login(String, String)}.
     * Tests if IllegalArgumentException works.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginIllegalArgument() {
        myRegistration1.login(" ", " ");
    }
    
    /**
     * Test method for {@link model.Registration#register(User)}.
     * Tests the object if null or not.
     */
    @Test
    public void testRegister() {
        assertNotNull("Object is null!", myRegistration1.register(myUser1));
    }

    /**
     * Test method for {@link model.Registration#register(User)}.
     * Tests if NullPointerException works.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterNull() {
        myUser1 = null;
        final Registration regi2 = new Registration();
        regi2.register(myUser1);
        
    }
    
    /**
     * Test method for {@link model.Registration#clear()}.
     * Tests to make sure that the clear() method works.
     */
    @Test
    public void testClear() {
        final Registration registration2 = new Registration();
        myRegistration1.clear();
        registration2.clear();
        assertSame("clear() method does not work!", registration2.getClass(), myRegistration1.getClass());
    }

    /**
     * Test method for {@link model.Registration#toString()}.
     * Tests to make sure that the toString() method returns the correct
     * string value.
     */
    @Test
    public void testToString() {
        assertEquals("toString() failed!", "Registration " + myRegistration1.getMyUserList().toString(), myRegistration1.toString());
    }

}
