/**
 * TCSS 305
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;

import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the User class to test the methods and constructors 
 * and to make sure that they are working correctly. Coverage of the User
 * class should be 100%.
 * 
 * @author Paul Lee (plee83@uw.edu)
 * @version Fall 2021
 *
 */
public class UserTest {

    /**
     * Class object User to be used to test the User class.
     */
    private User myUser1;
    
    /**
     * Class object User to be used to test the User class.
     */
    private User myUser2;
    
    /**
     * A method to initialize the User objects to test the methods
     * and constructors listed in the User class. 
     * @Before ensures that this method goes
     * first before going through the other test methods.
     */
    @Before
    public void setUp() {
        myUser1 = new User("name1", "pass1", true);
        myUser2 = new User("name2", "pass2");
    }

    /**
     * Test method for {@link model.User#User(String, String)}.
     * Tests to make sure that the object is not null and the username
     * and password values are set up correctly.
     */
    @Test
    public void testUserStringString() {
        assertNotNull("Object is null!", myUser2);
        assertEquals("Default string values are not correct!", "name2", myUser2.getMyName());
        assertEquals("Default string values are not correct!", "pass2", myUser2.getMyPassword());
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     * Tests if NullPointerException works.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringNull() {
        myUser2 = new User(null, null);
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     * Tests if IllegalArgumentException workss
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringIllegalArgument() {
        myUser2 = new User(" ", " ");
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     * Tests to make sure that the object is not null and the username,
     * password, and boolean of the VIP status values are set up correctly.
     */
    @Test
    public void testUserStringStringBoolean() {
        assertNotNull("Object is null!", myUser1);
        assertEquals("Default string values are not correct!", "name1", myUser1.getMyName());
        assertEquals("Default string values are not correct!", "pass1", myUser1.getMyPassword());
        assertEquals("Default boolean values are not correct!", true, myUser1.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     * Tests if NullPointerException works.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringBooleanNull() {
        myUser1 = new User(null, null, myUser1.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     * Tests if IllegalArgumentException works.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringBooleanIllegalArgument() {
        myUser1 = new User(" ", " ", myUser1.getMyVIPStatus());
    }
   
    /**
     * Test method for {@link model.User#getMyName()}.
     * Tests to make sure that the usernames are not null
     * and getting the name is the correct string value.
     */
    @Test
    public void testGetMyName() {
        assertNotNull("Object is null!", myUser1);
        assertNotNull("Object is null!", myUser2);
        assertEquals("Default string values are not correct!", "name1", myUser1.getMyName());
        assertEquals("Default string values are not correct!", "name2", myUser2.getMyName());
    }
    
    /**
     * Test method for {@link model.User#getMyPassword()}.
     * Tests to make sure that the passwords is not null and
     * getting the password is the correct string value.
     */
    @Test
    public void testGetMyPassword() {
        assertNotNull("Object is null!", myUser1);
        assertNotNull("Object is null!", myUser2);
        assertEquals("Default string values are not correct!", "pass1", myUser1.getMyPassword());
        assertEquals("Default string values are not correct!", "pass2", myUser2.getMyPassword());
    }
    
    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     * Tests to make sure that when the method is 
     * getting the VIP status, it is the correct boolean value.
     */
    @Test
    public void testGetMyVIPStatus() {
        assertEquals("Default values are not correct!", true, myUser1.getMyVIPStatus());
        assertEquals("Default values are not correct!", false, myUser2.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link model.User#toString()}.
     * Tests to make sure that the toString() method returns the correct
     * string value.
     */
    @Test
    public void testToString() {
        assertEquals("toString() failed!", "User (name2, pass2, false)", myUser2.toString());
        assertEquals("toString() failed!", "User (name1, pass1, true)", myUser1.toString());
    }
    
    /**
     * Test method for {@link model.User#equals(Object)}.
     * Tests to make sure that the equals(Object) method equals to
     * the other object.
     */
    @Test
    public void testEqualsObjectPositive() {
        final User user3 = new User("name1", "pass1", true);
        assertEquals("equals() failed!", myUser1.getClass(), user3.getClass());
        assertEquals("equals() failed!", myUser1, myUser1);
        assertTrue("equals() between two objects failed!", myUser1.equals(user3));
        
    }
    
    /**
     * Test method for {@link model.User#equals(Object)}.
     * Tests to make sure that the equals(Object) method does not
     * equal to the other object.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testEqualsObjectNegative() {
        assertNotEquals("equals() failed!", myUser1, myUser2);
        assertNotEquals("equals() failed!", myUser1, null);
        assertNotEquals("equals() failed!", myUser1, new ArrayList());
    }
    
    /**
     * Test method for {@link model.User#hashCode()}.
     * Tests to make sure that the hashCode() method is
     * working.
     */
    @Test
    public void testHashCode() {
        assertEquals("HashCode() failed!", Objects.hash(myUser1.getMyName(), myUser1.getMyPassword(), 
                                                        myUser1.getMyVIPStatus()), myUser1.hashCode());
        final User user3 = new User("name2", "pass2");
        assertEquals("HashCode() failed!", myUser2.hashCode(), user3.hashCode());
        
    }

}
