package address.data;
/**
 @author Lauren Dennedy
 @since February 2020
 @version 1.2
 **/

import java.sql.*;
import java.util.*;

/**
 * The AddressBook class represents an AddressBook
 * with a list for address entries and methods to
 * take in the addresses.
 */
public class AddressBook {
    /**
     * List of AddressEntry objects to comprise the AddressBook
     */
    private ArrayList<AddressEntry> addresses;

    /**
     * Private constructor to initialize the singleton and the addresses list
     */
    public AddressBook() {
        addresses = new ArrayList<>();
    }

    /**
     * Add an entry to the AddressBook's addresses list
     * @param e The entry added to the list
     */
    public void add(AddressEntry e){
        AddressEntry result;
        for (int i = 0; i < addresses.size(); i++) {
            result = addresses.get(i);

            //compare new objects last name to current element's last name
            if (e.getName().getLastName().toLowerCase().compareTo(result.getName().getLastName().toLowerCase()) <= 0) {

                //if we are here, then new object's last name is either equal to list object's last name
                // or ahead of it alphabetically, we check if last names are equal here
                if (e.getName().getLastName().toLowerCase().compareTo(result.getName().getLastName().toLowerCase()) == 0) {

                    //if last names are equal, check first name
                    //if first names are also equal, simply add at current index
                    if (e.getName().getLastName().toLowerCase().compareTo(result.getName().getFirstName().toLowerCase()) <= 0) {
                        addresses.add(i, e);
                        return;
                    }
                }
                addresses.add(i, e);
                return;
            }
        }
        addresses.add(e);
    }

    /**
     * Remove an entry from the AddressBook's addresses list in the same time
     * removes it from the data base
     * @param e The entry removed from the list
     */
    public void remove(AddressEntry e) throws SQLException {
        addresses.remove(e);
    }

    /**
     * a helper method that returns a list for the Jlist model
     * @return all the entries in the current address book class in the form of an array list
     */
    public ArrayList<AddressEntry> getList(){
        // One option would be to
        // return (ArrayList<AddressEntry>)addresses.clone();
        // but this generates a warning for an unchecked cast, we will use a slower/safer method
        ArrayList<AddressEntry> newList = new ArrayList<AddressEntry>();

        for (AddressEntry e: addresses) {
            newList.add(e);
        }

        return newList;
    }

    /**
     * Finds entries matching a string of the last name.
     * @param startOfLastName The string of the last name to search entries for
     * @return A list of matching entries
     */
    public List<AddressEntry> find(String startOfLastName) {
        // Final list of all matching entries
        List<AddressEntry> matchingEntries = new ArrayList<>();

        // Linear search for anything matching (Not super efficient but easy and lists are short)
        for (AddressEntry entry: addresses) {
            if(entry.getName().getLastName().toLowerCase().startsWith(startOfLastName.toLowerCase())){
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    /**
     * A method to populate the addresses list with data from Cloud
     */
    public void readFromDB() throws ClassNotFoundException, SQLException {

        //clear of the current address book for a fresh pull from the online DB
        //for most recent updates and items
        addresses.clear();

        // code from data base exercise
        Class.forName ("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement ();
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

        while(rset.next()){
            //visit each column to pull out their data
            /**
             * some place holding variables that holds pulled temporary data and
             * to be formatted into the current address book
             */
            String fName, lName, street, city, state, email, tel;
            int zip, ID ;
            //modify it via the order of data base
            fName = rset.getString(1);
            ID = Integer.parseInt(rset.getString(2));
            lName = rset.getString(3);
            street = rset.getString(4);
            city = rset.getString(5);
            state = rset.getString(6);
            zip = Integer.parseInt(rset.getString(7));
            email = rset.getString(8);
            tel = rset.getString(9);

            //add and sort
            this.add(new AddressEntry(fName, lName, street, city, state, zip, email, tel, ID));
        }

        // REMEMBER ALL THE CLOSING STATEMENT | VERY IMPORTANT
        rset.close();
        stmt.close();
        conn.close();

    }
}
