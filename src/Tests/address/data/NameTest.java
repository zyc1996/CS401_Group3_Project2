package Tests.address.data;

import address.data.Name;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameTest {

    @Test
    public void testToString(){
        Name name = new Name("firstname","lastname");
        assertEquals("firstname\nlastname\n",name.toString());
    }

    @Test
    public void setFirstName() {
        Name name = new Name();
        name.setFirstName("firstname");
        assertEquals("firstname",name.getFirstName());
    }

    @Test
    public void setLastName() {
        Name name = new Name();
        name.setLastName("lastname");
        assertEquals("lastname",name.getLastName());
    }

    @Test
    public void getFirstName() {
        Name name = new Name();
        name.setFirstName("firstname");
        assertEquals("firstname",name.getFirstName());
    }

    @Test
    public void getLastName() {
        Name name = new Name();
        name.setLastName("lastname");
        assertEquals("lastname",name.getLastName());
    }
}