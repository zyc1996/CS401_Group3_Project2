package address.data;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: The JUnit class for testing the Name class
 */
public class NameTest {

    /**
     * Method for testing Name's toString method
     */
    @Test
    public void testToString(){
        Name name = new Name("firstname","lastname");
        assertEquals("firstname\nlastname\n",name.toString());
    }

    /**
     * Method for testing Name's toString method with default data
     */
    @Test
    public void testToStringEmpty(){
        Name name = new Name();
        assertEquals("\n\n",name.toString());
    }

    /**
     * Method for testing Name's setter method for the first name
     */
    @Test
    public void setFirstName() {
        Name name = new Name();
        name.setFirstName("firstname");
        assertEquals("firstname",name.getFirstName());
    }

    /**
     * Method for testing Name's setter method for the first name with null
     */
    @Test
    public void setFirstNameNull() {
        Name name = new Name();
        name.setFirstName(null);
        assertEquals(null,name.getFirstName());
    }

    /**
     * Method for testing Name's setter method for the last name
     */
    @Test
    public void setLastName() {
        Name name = new Name();
        name.setLastName("lastname");
        assertEquals("lastname",name.getLastName());
    }

    /**
     * Method for testing Name's setter method for the last name with null
     */
    @Test
    public void setLastNameNull() {
        Name name = new Name();
        name.setLastName(null);
        assertEquals(null,name.getLastName());
    }

    /**
     * Method for testing Name's getter method for the first name
     */
    @Test
    public void getFirstName() {
        Name name = new Name();
        name.setFirstName("firstname");
        assertEquals("firstname",name.getFirstName());
    }

    /**
     * Method for testing Name's getter method for the first name with default data
     */
    @Test
    public void getFirstNameEmpty() {
        Name name = new Name();
        assertEquals("",name.getFirstName());
    }

    /**
     * Method for testing Name's getter method for the last name
     */
    @Test
    public void getLastName() {
        Name name = new Name();
        name.setLastName("lastname");
        assertEquals("lastname",name.getLastName());
    }

    /**
     * Method for testing Name's getter method for the last name with default data
     */
    @Test
    public void getLastNameEmpty() {
        Name name = new Name();
        assertEquals("",name.getLastName());
    }
}