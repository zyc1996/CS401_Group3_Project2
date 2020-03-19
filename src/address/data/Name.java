package address.data;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: A class that holds the name of a particular contact in an address book
 */
public class Name {
    /**
     * String variables to store first name, last name
     */
    private String firstName, lastName;

    /**
     * Empty constructor
     */
    public Name(){
        firstName = "";
        lastName = "";
    }

    /**
     * The default constructor
     * @param firstName string that holds first name
     * @param lastName string that holds last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Coverts object's name fields to a string for output
     * @return A string in the format "FirstName\n LastName\n"
     */
    @Override
    public String toString() {
        return firstName + "\n" + lastName + "\n";
    }

    /**
     * Setter method for first name
     * @param firstName string that holds first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter method for last name
     * @param lastName string that holds last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter method for last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
}
