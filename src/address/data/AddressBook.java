package address.data;
/**
 @author Lauren Dennedy
 @since February 2020
 @version 1.2
 **/

import java.net.Inet4Address;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

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

//    /**
//     * Public AddressBook Getter to call the private constructor
//     * @return the AddressBook to be used as the singleton
//     */
//    public AddressBook getAddressBook() {
//        if (addressBook == null) {
//            addressBook = new AddressBook();
//        }
//        return addressBook;
//    }

    /**
     * Add an entry to the AddressBook's addresses list
     * @param e The entry added to the list
     */
    public void add(AddressEntry e) {
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

        /**
         * Remove an entry from the AddressBook's addresses list
         * TODO: This method cannot remove solely on the case of lastName alone, because
         *       then it could remove more than one entry at a time and the user needs
         *       a choice in that. This method takes in an object for removal instead,
         *       and the choice granted by searching for last name is handled in the
         *       menu instead.
         * Unsure if the above to do still applies when implement with GUI
         * @param e The entry removed from the list
         */
    }

    public void remove(AddressEntry e) { addresses.remove(e); }

    /**
     * Prints out the entries of the addresses list with their toString methods
     */
    public void list(){
        for (AddressEntry e: addresses) {
            System.out.print(addresses.indexOf(e) + 1);
            System.out.println(e);
        }
    }

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

        // List of indices that match the searching algorithm
        List<Integer> matchingEntryIndices = new ArrayList<>();

        // List of just last name strings for comparison
        List<String> lastNameStrings = new ArrayList<>();

        // Populate the last name strings from the record of addresses
        for (AddressEntry entry: addresses) {
            lastNameStrings.add(entry.getName().getLastName());
        }

        // Comparator to find the first item containing
        Comparator<String> containsComparator = (o1, o2) -> {
            // Modified comparator so any string containing the first chars of last name is considered a complete match
            if (o1.toLowerCase().startsWith(o2.toLowerCase())) {
                return 0;
            } else {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        };

        // Adds the index of the first matching name to the matching indices list.
        matchingEntryIndices.add(Collections.binarySearch(lastNameStrings, startOfLastName, containsComparator));

        // Check any others after that
        int startIndex = matchingEntryIndices.get(0);

        // If at least one match was found, check linearly for any others after that (same last name)
        if (startIndex >= 0) {
            int i = startIndex + 1;
            while (i < lastNameStrings.size() && lastNameStrings.get(i).contains(startOfLastName)) {
                matchingEntryIndices.add(i);
                i++;
            }
        }

        // Populate the matching entries list from the matching indices and return it
        for (Integer j: matchingEntryIndices) {
            if (j >= 0) {
                matchingEntries.add(addresses.get(j));
            }
        }
        return matchingEntries;
    }

    /**
     * A method to populate the addresses list with data from Cloud
     */
    public void readFromDB() throws ClassNotFoundException, SQLException {

        // code from data base exercise
        Class.forName ("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin: Your_UserName_here / Your_PW_here @adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement ();
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

        while(rset.next()){
            //visit each column to pull out their data
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
