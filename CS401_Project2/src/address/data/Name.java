package address.data;

public class Name {
    /**
     * String variables to store first name, last name
     */
    private String firstName, lastName;

    /**
     * empty constructor
     */
    public Name(){
        firstName = "";
        lastName = "";
    }

    /**
     * default constructor
     * @param firstName string that holds first name
     * @param lastName string that holds last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * coverts object to string for output
     * @return object string
     */
    @Override
    public String toString() {
        return firstName + "\n" + lastName + "\n";
    }

    /**
     * varies system generated setter
     * @param firstName string that holds first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * varies system generated setter
     * @param lastName string that holds last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * varies system generated getter
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * varies system generated getter
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
}
