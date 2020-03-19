package Tests.address.data;

import address.data.Address;
import address.data.AddressBook;
import address.data.AddressEntry;
import address.data.Name;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddressBookTest {
    private AddressBook testBook = new AddressBook();
    private Name aeName = new Name("Hello", "Goodbye");
    private Address aeAddress = new Address("street", "city", "state", 6832);
    private AddressEntry ae = new AddressEntry("email", "phone", aeName, aeAddress, 5);

    @Test
    public void add() {
        testBook.add(ae);
        assertEquals(testBook.find("G").get(0), ae);
    }

    @Test
    public void remove() {
        testBook.add(ae);
        try {
            testBook.remove(testBook.find("G").get(0));
        } catch (SQLException e) {
            System.out.println("could not remove");
        }
    }

    @Test
    public void getList() {
        testBook.add(ae);
        ArrayList<AddressEntry> testList = new ArrayList<AddressEntry>();
        testList.add(ae);
        assertEquals(testBook.getList(), testList);
    }

    @Test
    public void find() {
        testBook.add(ae);
        assertEquals(testBook.find("G").get(0), ae);
    }

    @Test
    public void readFromDB() {
        boolean pass;
        try {
            testBook.readFromDB();
            pass = true;
        } catch (SQLException e) {
            System.out.println("could not remove");
            pass = false;
        } catch (ClassNotFoundException e) {
            System.out.println("could not remove");
            pass = false;
        }
        assertEquals(true, pass);
    }
}