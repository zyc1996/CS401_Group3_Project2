package address.gui;

import address.data.AddressBook;
import address.data.AddressEntry;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class AddressBookGuiTest {

    @Test
    public void addToDB() throws SQLException, ClassNotFoundException {
        //create a new entry
        AddressEntry ae = new AddressEntry("firstname","lastname","street","city","state",123,"email","phone",123);

        //push it to DB
        AddressBookGui.addToDB(ae);

        ArrayList<AddressEntry> test = new ArrayList<>();
        /**
         * I'm not sure what is the Query command to retrieve the newest added row in the DB
         * but since this project's DB's PK is updated via increment per new entry method, I'm
         * going to retrieve the whole DB in Ascending key order and get the last value as the
         * newest added entry, not using ReadFromDB method of the addressBook class due to a
         * different adding method that involves different sorting
         */

        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE ORDER BY ID ASC");

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
            test.add(new AddressEntry(fName, lName, street, city, state, zip, email, tel, ID));
        }

        //set test entry ID equal to the one with data base provided
        ae.setID(test.get(test.size()-1).getID());

        assertEquals(ae.toString(),test.get(test.size()-1).toString());
    }

    @Test
    public void testRemoveFromDB() throws SQLException, ClassNotFoundException {
        AddressBook ab = new AddressBook();
        ab.readFromDB();

        //select a random ID from the ab and save it as the testing variable
        int index = new Random().nextInt(ab.getList().size());
        AddressEntry ae = ab.getList().get(index);

        //removes the entry from DB
        AddressBookGui.removeFromDB(ae);

        //do a fresh pull of updated entries from DB
        ab.readFromDB();
        ArrayList<AddressEntry> test = ab.getList();

        for(AddressEntry e: test){
            assertNotEquals(ae, e);
        }
    }

    @Test
    public void TestEditDB() throws SQLException, ClassNotFoundException {
        AddressBook ab = new AddressBook();
        ab.readFromDB();

        //select a random ID from the ab and save it as the testing variable
        int index = new Random().nextInt(ab.getList().size());
        AddressEntry ae = ab.getList().get(index);
        int ID = ae.getID();

        //modify the ae entry and push it to DB
        ae.setName("firstname","lastname");
        AddressBookGui.editDB(ae);

        //do a fresh pull of updated entries from DB
        ab.readFromDB();
        ArrayList<AddressEntry> test = ab.getList();

        AddressEntry ae2 = new AddressEntry();
        for(AddressEntry e: test){
            if(e.getID() == ID){
                ae2 = e;
            }
        }

        assertEquals(ae.toString(),ae2.toString()); //WHY ARE THEY NOT EQUAL??????????????????????????
    }
}