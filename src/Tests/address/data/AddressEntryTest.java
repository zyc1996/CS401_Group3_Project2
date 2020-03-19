package address.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressEntryTest {

    @Test
    public void testGetPhone() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setPhone("phone");
        assertEquals("phone",addressentry.getPhone());
    }

    @Test
    public void testSetPhone() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setPhone("phone");
        assertEquals("phone",addressentry.getPhone());
    }

    @Test
    public void testGetEmail() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setEmail("email");
        assertEquals("email",addressentry.getEmail());
    }

    @Test
    public void testSetEmail() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setEmail("email");
        assertEquals("email",addressentry.getEmail());
    }

    @Test
    public void testSetID() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setID(123);
        assertEquals(123,addressentry.getID());
    }

    @Test
    public void testGetID() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setID(123);
        assertEquals(123,addressentry.getID());
    }

    @Test
    public void testSetName() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setName("firstname","lastname");
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }

    @Test
    public void testSetName2(){
        AddressEntry addressentry = new AddressEntry();
        Name name = new Name("firstname","lastname");
        addressentry.setName(name);
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }


    @Test
    public void testSetAddress() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setAddress("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    @Test
    public void testSetAddress2() {
        AddressEntry addressentry = new AddressEntry();
        Address address = new Address("street","city","state",123);
        addressentry.setAddress(address);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    @Test
    public void testGetName() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setName("firstname","lastname");
        assertEquals("firstname\nlastname\n",addressentry.getName().toString());
    }

    @Test
    public void testGetAddress() {
        AddressEntry addressentry = new AddressEntry();
        addressentry.setAddress("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",addressentry.getAddress().toString());
    }

    @Test
    public void testToString() {
        AddressEntry addressentry = new AddressEntry("firstname","lastname","street","city","state",123,"email","phone",123);
        assertEquals("123\nfirstname\nlastname\nstreet\ncity\nstate\n123\nemail\nphone\n",addressentry.toString());
    }
}