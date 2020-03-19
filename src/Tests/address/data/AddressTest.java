package Tests.address.data;

import address.data.Address;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void testToString() {
        Address address = new Address("street","city","state",123);
        assertEquals("street\ncity\nstate\n123\n",address.toString());
    }

    @Test
    public void getStreet() {
        Address address = new Address();
        address.setStreet("street");
        assertEquals("street", address.getStreet());
    }

    @Test
    public void getCity() {
        Address address = new Address();
        address.setCity("city");
        assertEquals("city",address.getCity());
    }

    @Test
    public void getState() {
        Address address = new Address();
        address.setState("state");
        assertEquals("state",address.getState());
    }

    @Test
    public void getZip() {
        Address address = new Address();
        address.setZip(123);
        assertEquals(123,address.getZip());
    }

    @Test
    public void setStreet() {
        Address address = new Address();
        address.setStreet("street");
        assertEquals("street", address.getStreet());
    }

    @Test
    public void setCity() {
        Address address = new Address();
        address.setCity("city");
        assertEquals("city",address.getCity());
    }

    @Test
    public void setState() {
        Address address = new Address();
        address.setState("state");
        assertEquals("state",address.getState());
    }

    @Test
    public void setZip() {
        Address address = new Address();
        address.setZip(123);
        assertEquals(123,address.getZip());
    }
}