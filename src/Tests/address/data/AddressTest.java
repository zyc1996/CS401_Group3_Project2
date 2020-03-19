package address.data;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: The JUnit class for testing the Address class
 */
public class AddressTest {

    /**
     * Method for testing Address' toString method
     */
    @Test
    public void testToString() {
        Address address = new Address("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",address.toString());
    }

    /**
     * Method for testing Address' getter method for street
     */
    @Test
    public void getStreet() {
        Address address = new Address();
        address.setStreet("street");
        assertEquals("street", address.getStreet());
    }

    /**
     * Method for testing Address' getter method for city
     */
    @Test
    public void getCity() {
        Address address = new Address();
        address.setCity("city");
        assertEquals("city",address.getCity());
    }

    /**
     * Method for testing Address' getter method for state
     */
    @Test
    public void getState() {
        Address address = new Address();
        address.setState("state");
        assertEquals("state",address.getState());
    }

    /**
     * Method for testing Address' getter method for zip code
     */
    @Test
    public void getZip() {
        Address address = new Address();
        address.setZip(123);
        assertEquals(123,address.getZip());
    }

    /**
     * Method for testing Address' setter method for street
     */
    @Test
    public void setStreet() {
        Address address = new Address();
        address.setStreet("street");
        assertEquals("street", address.getStreet());
    }

    /**
     * Method for testing Address' setter method for city
     */
    @Test
    public void setCity() {
        Address address = new Address();
        address.setCity("city");
        assertEquals("city",address.getCity());
    }

    /**
     * Method for testing Address' setter method for state
     */
    @Test
    public void setState() {
        Address address = new Address();
        address.setState("state");
        assertEquals("state",address.getState());
    }

    /**
     * Method for testing Address' setter method for zip
     */
    @Test
    public void setZip() {
        Address address = new Address();
        address.setZip(123);
        assertEquals(123,address.getZip());
    }
}