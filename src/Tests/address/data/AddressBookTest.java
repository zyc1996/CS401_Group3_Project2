package address.data;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: The JUnit class for testing the AddressBook class
 */
public class AddressBookTest {
    /**
     * AddressBook class testing object
     */
    private AddressBook testBook;

    /**
     * Name class testing object
     */
    private Name aeName;

    /**
     * Address class testing object
     */
    private Address aeAddress;

    /**
     * AddressEntry class testing object
     */
    private AddressEntry ae;

    /**
     * Helper method to initialize variables before every test
     */
    @Before
    public void init(){
        testBook = new AddressBook();
        aeName = new Name("Hello", "Goodbye");
        aeAddress = new Address("street", "city", "state", 6832);
        ae = new AddressEntry("email", "phone", aeName, aeAddress, 5);
    }

    /**
     * Method to test AddressBook's add method
     */
    @Test
    public void add() {
        testBook.add(ae);
        assertEquals(testBook.find("G").get(0), ae);
    }

    /**
     * Method to test AddressBook's remove method
     */
    @Test
    public void remove() {
        testBook.add(ae);
        try {
            testBook.remove(testBook.find("G").get(0));
        } catch (SQLException e) {
            System.out.println("could not remove");
        }
    }
    /**
     * Method to test AddressBook's get list method
     */
    @Test
    public void getList() {
        testBook.add(ae);
        ArrayList<AddressEntry> testList = new ArrayList<AddressEntry>();
        testList.add(ae);
        assertEquals(testBook.getList(), testList);
    }

    /**
     * Method to test AddressBook's find method
     */
    @Test
    public void find() {
        testBook.add(ae);
        assertEquals(testBook.find("G").get(0), ae);
    }

    /**
     * Method to test AddressBook's method to read in from the database
     */
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