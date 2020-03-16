package address.data;

public class Address {
    /**
     * String variables that stores street, city, state
     */
    private String street, city, state;
    private int zip;

    /**
     * Empty constructor
     */
    public Address() {
        street = "";
        city = "";
        state = "";
        zip = 0;
    }

    /**
     * default constructor
     * @param street holds street name in string
     * @param city holds city name in string
     * @param state holds state name in string
     * @param zip holds zip in integer
     */
    public Address(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * convert this object to string
     * @return a string containing all the variables
     */
    @Override
    public String toString(){
        return street + "\n" + city + "\n" + state + "\n" + zip + "\n";
    }

    /**
     * varies system generated getter
     * @return street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * varies system generated getter
     * @return city name
     */
    public String getCity() {
        return city;
    }

    /**
     * varies system generated getter
     * @return state name
     */
    public String getState() {
        return state;
    }

    /**
     * varies system generated getter
     * @return zip code
     */
    public int getZip() {
        return zip;
    }

    /**
     * varies system generated setter
     * @param street a string that holds street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * varies system generated setter
     * @param city a string that holds city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * varies system generated setter
     * @param state a string that holds state name
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * varies system generated setter
     * @param zip an integer that hold zip code
     */
    public void setZip(int zip) {
        this.zip = zip;
    }
}
