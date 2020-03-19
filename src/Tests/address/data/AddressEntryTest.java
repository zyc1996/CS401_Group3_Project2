package address.data;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: The JUnit class for testing the AddressEntry class
 */
public class AddressEntryTest {

    /**
     * Testing the AddressEntry's getter method for phone
     */
    @Test
    public void testGetPhone() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setPhone("phone");
        assertEquals("phone",addressentry.getPhone());
    }

    /**
     * Testing the AddressEntry's getter method for phone with default data
     */
    @Test
    public void testGetPhoneEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals("",addressentry.getPhone());
    }

    /**
     * Testing the AddressEntry's setter method for phone
     */
    @Test
    public void testSetPhone() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setPhone("phone");
        assertEquals("phone",addressentry.getPhone());
    }

    /**
     * Testing the AddressEntry's setter method for phone with null string
     */
    @Test
    public void testSetPhoneNull() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setPhone(null);
        assertEquals(null,addressentry.getPhone());
    }

    /**
     * Testing the AddressEntry's getter method for email
     */
    @Test
    public void testGetEmail() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setEmail("email");
        assertEquals("email",addressentry.getEmail());
    }

    /**
     * Testing the AddressEntry's getter method for email with default data
     */
    @Test
    public void testGetEmailEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals("",addressentry.getEmail());
    }

    /**
     * Testing the AddressEntry's setter method for email
     */
    @Test
    public void testSetEmail() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setEmail("email");
        assertEquals("email",addressentry.getEmail());
    }

    /**
     * Testing the AddressEntry's setter method for email with null string
     */
    @Test
    public void testSetEmailNull() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setEmail(null);
        assertEquals(null,addressentry.getEmail());
    }

    /**
     * Testing the AddressEntry's setter method for ID
     */
    @Test
    public void testSetID() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setID(123);
        assertEquals(123,addressentry.getID());
    }

    /**
     * Testing the AddressEntry's setter method for ID with negative value
     */
    @Test
    public void testSetIDNegative() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setID(-1);
        assertEquals(-1,addressentry.getID());
    }

    /**
     * Testing the AddressEntry's getter method for ID
     */
    @Test
    public void testGetID() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setID(123);
        assertEquals(123,addressentry.getID());
    }

    /**
     * Testing the AddressEntry's getter method for ID with default data
     */
    @Test
    public void testGetIDEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals(0,addressentry.getID());
    }


    /**
     * Testing the AddressEntry's setter method for Name using strings
     */
    @Test
    public void testSetName() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setName("firstname","lastname");
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }

    /**
     * Testing the AddressEntry's setter method for Name using a Name object
     */
    @Test
    public void testSetName2(){
        AddressEntry addressentry = new AddressEntry();
        Name name = new Name("firstname","lastname");
        addressentry.setName(name);
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }

    /**
     * Testing the AddressEntry's setter method for Address using strings
     */
    @Test
    public void testSetAddress() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setAddress("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    /**
     * Testing the AddressEntry's setter method for Address using an Address object
     */
    @Test
    public void testSetAddress2() {
        AddressEntry addressentry = new AddressEntry();
        Address address = new Address("street","city","state",123);
        addressentry.setAddress(address);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    /**
     * Testing the AddressEntry's getter method for Name
     */
    @Test
    public void testGetName() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setName("firstname","lastname");
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }

    /**
     * Testing the AddressEntry's getter method for Name with default data
     */
    @Test
    public void testGetNameEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals("\n\n",addressentry.getName().toString());
    }

    /**
     * Testing the AddressEntry's getter method for Address
     */
    @Test
    public void testGetAddress() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setAddress("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    /**
     * Testing the AddressEntry's getter method for Address with default data
     */
    @Test
    public void testGetAddressEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals("\n\n\n0\n",addressentry.getAddress().toString());
    }

    /**
     * Testing AddressEntry's toString method
     */
    @Test
    public void testToString() {
        AddressEntry addressentry = new AddressEntry("firstname","lastname","street","city","state",123,"email","phone",123);
        assertEquals("123\nfirstname\nlastname\nstreet\ncity\nstate\n123\nemail\nphone\n",addressentry.toString());
    }

    /**
     * Testing AddressEntry's toString method with default data
     */
    @Test
    public void testToStringEmpty() {
        AddressEntry addressentry = new AddressEntry();
        assertEquals("0\n\n\n\n\n\n0\n\n\n",addressentry.toString());
    }
}