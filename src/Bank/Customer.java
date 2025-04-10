package Bank;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String city;

    public Customer() {
    }
    public Customer(String firstName, String lastName, String city) {
        setFirstName(firstName);
        setLastName(lastName);
        setCity(city);
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override
    public String toString() {
        return "Customer" + "   customerId  =   " + customerId + ",  firstName   =   " + firstName + ",  lastName    =   " + lastName + ",  city        =   " + city;
    }
}
