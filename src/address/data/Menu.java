//package address.data; /**
// @author Lauren Dennedy
// @since February 2020
// @version 1.2
// **/
//
///**
// * this class is probably no longer needed but leaving it here just in case
// */
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * The Menu class represents the menu the user
// * interacts with at the console level
// */
//public class Menu {
//    /**
//     * Single Menu instance (singleton)
//     */
//    private static Menu menu = null;
//
//    /**
//     * Scanner object for the menu class to read user input
//     */
//    private static Scanner reader;
//
//    /**
//     * Private constructor to initialize the singleton and scanner
//     */
//    private Menu() { reader = new Scanner(System.in); }
//
//    /**
//     * Public Menu Getter to call the private constructor
//     * @return the Menu to be used as the singleton
//     */
//    public static Menu getMenu() {
//        if (menu == null) {
//            menu = new Menu();
//        }
//        return menu;
//    }
//
//    /**
//     * Method to display the menu and handle inputs to redirect to other methods
//     */
//    public static boolean display() {
//        System.out.println("\n============================================");
//        System.out.println("Options:");
//        System.out.println("A.) Load a file ");
//        System.out.println("B.) Add an entry");
//        System.out.println("C.) Remove an entry");
//        System.out.println("D.) Find entries");
//        System.out.println("E.) List the Address Book");
//        System.out.println("F.) Quit");
//        System.out.println("============================================");
//        System.out.print("\nPlease enter a letter for your selection: ");
//        String in = reader.nextLine().toUpperCase();
//        while (!(in.equals("A") || in.equals("B") || in.equals("C") || in.equals("D") || in.equals("E") || in.equals("F"))) {
//            System.out.print("\nPlease enter a letter for your selection: ");
//            in = reader.nextLine().toUpperCase();
//        }
//
//        switch (in.charAt(0)) {
//            case 'A': {
//                readin(in);
//                return false;
//            }
//            case 'B': {
//                add(in);
//                return false;
//            }
//            case 'C': {
//                remove(in);
//                return false;
//            }
//            case 'D': {
//                search(in);
//                return false;
//            }
//            case 'E': {
//                list();
//                return false;
//            }
//            case 'F': {
//                System.out.println("Thank you for using the Address Book! Goodbye!");
//                return true;
//            }
//            default: {
//                return false;
//            }
//        }
//    }
//
//    /**
//     * A method that handles entries read in from file
//     * @param in string that holds input
//     */
//    public static void readin(String in){
//        System.out.print("\nPlease enter a filename to read: ");
//        in = reader.nextLine();
//        in = "C:\\Users\\yuche\\Desktop\\School Files\\CSUEB\\Spring2020\\CS 401\\CS401_Group3_Project2\\CS401_Project2\\src\\" + in;
//        try {
//            // Temporarily create these objects to make sure string name is valid
//            File f = new File(in);
//            Scanner t = new Scanner(f);
//            t.close();
//            // Free the memory after this point if no exception was thrown
//            t = null;
//            f = null;
//            // Call AddressBook to read the file
//            AddressBook.readFromFile(in);
//        } catch (FileNotFoundException e) {
//            System.out.println("The file \'" + in + "\' could not be found.");
//        }
//    }
//
//    /**
//     * A method that add an entry to the address Book
//     * @param in string that holds input
//     */
//    public static void add(String in){
//        String fName, lName, street, city, state, tel, email;
//        int zip, ID;
//
//        // Prompt menu commands to accept a new address
//        fName = promptFirstName();
//        lName = promptLastName();
//
//        street = promptStreet();
//        city = promptCity();
//        state = promptState();
//        zip = promptZip();
//
//        tel = promptTelephone();
//        email = promptEmail();
//        ID = promptID();
//
//        // Add new address once complete
//        AddressBook.add(new AddressEntry(fName, lName, street, city, state, zip, email, tel, ID));
//        AddressBook.sort();
//    }
//
//    /**
//     * A method that removes an entry from the address Book
//     * @param in string that holds input
//     */
//    public static void remove(String in){
//        System.out.print("Enter a last name to search for removal: ");
//        in = reader.nextLine();
//        List<AddressEntry> entries = AddressBook.find(in);
//        if (entries.size() >= 1) {
//            int tracker = 1;
//            System.out.println("The following entries were found: ");
//            for (AddressEntry entry: entries) {
//                System.out.println(tracker + ": " + entry);
//                tracker++;
//            }
//
//            int num = 0;
//            System.out.print("Which would you like to remove? ");
//            try {
//                num = Integer.parseInt(reader.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("That's not a valid number.");
//            }
//
//            while (num > tracker || num <= 0) {
//                System.out.print("Which would you like to remove? ");
//                try {
//                    num = Integer.parseInt(reader.nextLine());
//                } catch (NumberFormatException e) {
//                    System.out.println("That's not a valid number.");
//                }
//            }
//            AddressBook.remove(entries.get(num-1));
//
//        } else {
//            System.out.println("No matching entries were found.");
//        }
//        AddressBook.sort();
//    }
//
//    /**
//     * A method that search for an entry in the address Book
//     * @param in string that holds input
//     */
//    public static void search(String in){
//        System.out.print("Enter a last name to search for: ");
//        in = reader.nextLine();
//        List<AddressEntry> entries = AddressBook.find(in);
//        if (entries.size() >= 1) {
//            System.out.println("The following entries were found: ");
//            for (AddressEntry entry: entries) {
//                System.out.println(entry);
//            }
//        } else {
//            System.out.println("No matching entries were found.");
//        }
//    }
//
//    /**
//     * a method that display all entries in the address book
//     */
//    public static void list(){
//        AddressBook.list();
//    }
//    /**
//     * Prompt method to enter a first name
//     * @return The first name the user entered as a string
//     */
//    public static String promptFirstName() {
//        System.out.print("First Name: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter a last name
//     * @return The last name the user entered as a string
//     */
//    public static String promptLastName() {
//        System.out.print("Last Name: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter a street
//     * @return The street the user entered as a string
//     */
//    public static String promptStreet() {
//        System.out.print("Street: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter a city
//     * @return The city the user entered as a string
//     */
//    public static String promptCity() {
//        System.out.print("City: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter a state
//     * @return The state the user entered as a string
//     */
//    public static String promptState() {
//        System.out.print("State: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter a zip code
//     * @return The zip code the user entered as an integer
//     */
//    public static Integer promptZip() {
//        System.out.print("Zip Code: ");
//        String line = reader.nextLine();
//        int zip = 0;
//
//        // Input validation/exception checking for integer input
//        try {
//            zip = Integer.parseInt(line);
//        } catch (NumberFormatException e) {
//            System.out.println("Improper zip was entered.");
//        }
//        return zip;
//    }
//
//    /**
//     * Prompt method to enter a phone number
//     * @return The phone number the user entered as a string
//     */
//    public static String promptTelephone() {
//        System.out.print("Telephone Number: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter an email address
//     * @return The email address the user entered as a string
//     */
//    public static String promptEmail() {
//        System.out.print("Email: ");
//        return reader.nextLine();
//    }
//
//    /**
//     * Prompt method to enter an ID
//     * @return The ID the user entered as a integer
//     */
//    public static int promptID() {
//        System.out.print("ID: ");
//        String line = reader.nextLine();
//        int ID = 0;
//
//        // Input validation/exception checking for integer input
//        try {
//            ID = Integer.parseInt(line);
//        } catch (NumberFormatException e) {
//            System.out.println("Improper zip was entered.");
//        }
//        return ID;
//    }
//}
