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
     * Method to test AddressBook's add method automatic sorting
     */
    @Test
    public void addOrder() {

        //entry for Hello Goodbye
        testBook.add(ae);

        //this name should be put before the Hello Goodbye alphabetically
        aeName = new Name("Another", "Goodbye");
        aeAddress = new Address("street", "city", "state", 6832);
        ae = new AddressEntry("email", "phone", aeName, aeAddress, 5);
        testBook.add(ae);

        //New entry is put before first entry and not after
        assertEquals(testBook.getList().get(0), ae);
        assertNotSame(testBook.getList().get(1), ae);
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
     * Method to test AddressBook's remove method with duplicate entries
     */
    @Test
    public void removeDuplicate() {
        testBook.add(ae);
        testBook.add(ae);
        try {
            testBook.remove(testBook.find("G").get(0));
        } catch (SQLException e) {
            System.out.println("could not remove");
        }
        assertEquals(testBook.find("G").get(0), ae);
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
     * Method to test AddressBook's get list method as you add more entries
     */
    @Test
    public void getListConsistency() {
        ArrayList<AddressEntry> testList = new ArrayList<AddressEntry>();
        assertEquals(testBook.getList(), testList);

        for(int i = 0; i < 5; i++) {
            testBook.add(ae);
            testList.add(ae);
            assertEquals(testBook.getList(), testList);
        }
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
     * Method to test AddressBook's find method with no result
     */
    @Test
    public void findNull() {
        //compare to empty array
        ArrayList<AddressEntry> testList = new ArrayList<AddressEntry>();
        testBook.add(ae);
        assertEquals(testBook.find("A"), testList);
    }

    /**
     * Method to test AddressBook's method to read in from empty database
     */
    @Test
    public void readFromDB() {
        boolean pass;
        try {
            testBook.readFromDB();
            pass = true;
        } catch (SQLException e) {
            System.out.println("could not connect to DB");
            pass = false;
        } catch (ClassNotFoundException e) {
            System.out.println("could not connect to DB");
            pass = false;
        }
        assertEquals(true, pass);
    }

    /**
     * Method to test AddressBook's method to read in from database with entries
     */
    @Test
    public void readFromDBFilled() {
        testBook.add(ae);
        testBook.add(ae);
        testBook.add(ae);
        boolean pass;
        try {
            testBook.readFromDB();
            pass = true;
        } catch (SQLException e) {
            System.out.println("could not connect to DB");
            pass = false;
        } catch (ClassNotFoundException e) {
            System.out.println("could not connect to DB");
            pass = false;
        }
        assertEquals(true, pass);
    }
}