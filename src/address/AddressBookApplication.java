package address;
import address.gui.AddressBookGui;
import org.junit.*;

import java.sql.SQLException;
 /**
  * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
  * @since  March 2020, SDK 13
  * @version 2.0
  *
  * purpose: Main class of the program. Initiates the user GUI
  */
public class AddressBookApplication {
    /**
     * The main method that starts the program GUI
     * @param args Arguments to accept when calling main
     * @throws SQLException Possible failure in executing SQL
     * @throws ClassNotFoundException Possible class not found
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * A new GUI declaration to create the Address Book Frame clas
         */
        AddressBookGui myGui = new AddressBookGui();

        // Initialize program GUI
        myGui.init();

        // Run test classes:
        // AddressBookTest bookTest = new AddressBookTest();
        // AddressEntryTest entryTest = new AddressEntryTest();
        // MenuTest menuTest = new MenuTest();
    }
}
