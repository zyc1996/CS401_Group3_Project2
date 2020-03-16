package address;
/**
 @author Lauren Dennedy
 @since February 2020
 @version 1.2
 **/

import address.data.AddressBook;
import address.data.AddressEntry;
import address.gui.AddressBookGui;
//import address.data.Menu;

/**
 * The Main class used to start the menu.
 */
public class AddressBookApplication {
    /**
     * The main method to start the address.AddressBookApplication
     * @param args Arguments to accept when calling main
     */
    public static void main(String[] args) {
        AddressBook AB = new AddressBook();
        AddressBookGui myGui = new AddressBookGui();
        myGui.init();

        AB.add(new AddressEntry("test", "abc", "somewhere 123",
                "someplace", "somestate", 123, "email", "tel", 1));
        AB.add(new AddressEntry("test", "dude", "somewhere 123",
                "someplace", "somestate", 123, "email", "tel", 2));
        AB.add(new AddressEntry("test", "abc", "somewhere new, should be 1st in list",
                "someplace", "somestate", 123, "email", "tel", 3));
        AB.add(new AddressEntry("test", "bay", "should be third in list",
                "someplace", "somestate", 123, "email", "tel", 2));
        AB.list();
//        Menu.getMenu();
//
//        boolean quit = false;
//        while(!quit) {
//            quit = Menu.display();
//        }

        // Run test classes:
        // AddressBookTest bookTest = new AddressBookTest();
        // AddressEntryTest entryTest = new AddressEntryTest();
        // MenuTest menuTest = new MenuTest();
    }
}
