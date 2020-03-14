package address.data; /**
 @author Lauren Dennedy
 @since February 2020
 @version 1.2
 **/

/**
 * The AddressEntry class represents a specific
 * address entry to be placed in an AddressBook object
 */
public class AddressEntry {
    /**
     * String variables to store email, phone
     */
    private String email, phone;

    /**
     * private name object
     */
    private Name name;
    /**
     * private address object
     */
    private Address address;

    /**
     * Integer variable to store the ID
     */
    private Integer ID;

    /**
     * Empty constructor for the address entry, sets all values to empty/zero
     */
    public AddressEntry() {
        name = new Name();
        address = new Address();
        phone = "";
        email = "";
        ID = 0;
    }

    /**
     * constructor with object read in
     * @param email string that holds email address
     * @param phone string that hold phone number
     * @param name object that holds name object
     * @param address object that holds address object
     * @param ID integer that holds object ID
     */
    public AddressEntry(String email, String phone, Name name, Address address, int ID) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.ID = ID;
    }

    /**
     * constructor with regular variables
     * @param firstName string that holds first name
     * @param lastName string that holds last name
     * @param street string that holds street name
     * @param city string that holds city name
     * @param state string that holds state name
     * @param zip integer that holds zip code
     * @param email string that holds email address
     * @param phone string that holds phone number
     * @param ID integer that holds ID
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
     * ID Code Getter
     * @return the zip code as an integer
     */
    public int getID() { return ID; }

    /**
     * ID Code Setter
     * @param ID The integer to be set as the zip code
     */
    public void setZip(int ID) { this.ID = ID; }

    /**
     * name object setter that takes object
     * @param name a name object
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * name object setter that takes string
     * @param firstName string that holds first name
     * @param lastName string that holds last name
     */
    public void setName(String firstName, String lastName) {
        this.name = new Name(firstName,lastName);
    }

    /**
     * address object setter that takes object
     * @param address an address object
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * address object setter that takes strings
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
