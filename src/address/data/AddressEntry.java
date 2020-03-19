package address.data;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * purpose: The AddressEntry class represents a single entry of an Address Book
 * Contains variables containing the name, address, phone, and email of the contact.
 */
public class AddressEntry {
    /**
     * Private variables to store email & phone strings
     */
    private String email, phone;

    /**
     * Private name object containing first & last name
     */
    private Name name;

    /**
     * Private address object
     */
    private Address address;

    /**
     * Private ID integer for use as a primary key in the database
     */
    private Integer ID;

    /**
     * Empty constructor for the entry, sets all values to empty/zero
     */
    public AddressEntry() {
        name = new Name();
        address = new Address();
        phone = "";
        email = "";
        ID = 0;
    }

    /**
     * Constructor that takes in parameters for the various fields of the entry
     * This version takes in Address & Name objects instead of strings
     * @param email The contact's email address
     * @param phone The contact's phone number
     * @param name Name object holding the contact's name
     * @param address Address object holding the contact's address
     * @param ID Will be used as the the object's ID
     */
    public AddressEntry(String email, String phone, Name name, Address address, int ID) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.ID = ID;
    }

    /**
     * Constructor that takes in parameters for the various fields of the entry
     * This version takes in strings instead of Address & Name object
     * @param ID Will be used as the the object's ID
     * @param firstName First name of the contact
     * @param lastName Last name of the contact
     * @param street Street field of the class Address object
     * @param city City field of the class Address object
     * @param state State of the class Address object
     * @param zip Zip of the class Address object
     * @param email The contact's email address
     * @param phone The contact's phone number
     */
    public AddressEntry(String firstName, String lastName, String street, String city, String state, int zip, String email, String phone, int ID) {
        name = new Name(firstName,lastName);
        address = new Address(street,city,state,zip);
        this.email = email;
        this.phone = phone;
        this.ID = ID;
    }

    /**
     * Phone Number Getter
     * @return the phone number as a string
     */
    public String getPhone() { return phone; }

    /**
     * Phone Number Setter
     * @param phone The string to be set as the phone number
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Email Getter
     * @return the email address as a string
     */
    public String getEmail() { return email; }

    /**
     * Phone Number Setter
     * @param email The string to be set as the email address
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * setter for ID variable
     * @param ID the id number
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * ID Code Getter
     * @return the zip code as an integer
     */
    public int getID() { return ID; }

    /**
     * Name object setter that takes an object
     * @param name a name object
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Name object setter
     * This version takes strings instead of a Name object
     * @param firstName string that holds first name
     * @param lastName string that holds last name
     */
    public void setName(String firstName, String lastName) {
        this.name = new Name(firstName,lastName);
    }

    /**
     * address object setter that takes an object
     * @param address an address object
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * address object setter
     * This version takes strings instead of an Address object
     * @param street string that holds street name
     * @param city string that holds city name
     * @param state string that holds state name
     * @param zip integer that holds zip code
     */
    public void setAddress(String street, String city, String state, int zip) {
        this.address = new Address(street,city,state,zip);
    }

    /**
     * varies system generated getter
     * @return name object
     */

    public Name getName() {
        return name;
    }

    /**
     * varies system generated getter
     * @return address object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * toString Method for AddressEntry
     * @return a formatted string of the full contact information of the entry
     */
    @Override
    public String toString() {
        return ID + "\n" + name.toString() + address.toString() + email + "\n" + phone + "\n";
    }
}
