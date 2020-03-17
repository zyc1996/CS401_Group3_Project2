package address;
/**
 @author Lauren Dennedy
 @since February 2020
 @version 1.2
 **/

import address.gui.AddressBookGui;

import java.sql.SQLException;

/**
 * The Main class used to start the menu.
 */
public class AddressBookApplication {
    /**
     * The main method to start the address.AddressBookApplication
     * @param args Arguments to accept when calling main
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * A new GUI declaration to create the Address Book Frame class
         */
        AddressBookGui myGui = new AddressBookGui();

        //initialize it here
        myGui.init();

        // Run test classes:
        // AddressBookTest bookTest = new AddressBookTest();
        // AddressEntryTest entryTest = new AddressEntryTest();
        // MenuTest menuTest = new MenuTest();
    }
}
