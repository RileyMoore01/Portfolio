import java.io.FileWriter;
import java.io.*; 
import java.io.IOException;

// Customer class is to perfom the storing of customer information as well as mutating and accessing it.
public class CustomerV2 {

    //private instance variables used for the Customer class
    String firstName;
    String lastName;
    String rewardNum;
    String status;
    double discountOrStarsEarned;
    String email;
    String mobile;

    int numberOfCustomers = 10;
    int count = 0;
    CustomerV2[] customerArray = new CustomerV2[numberOfCustomers];

    //---Constructors for Customer---
    //Default Constructor
    public CustomerV2(){
        firstName = "";
        lastName = "";
        rewardNum = "";
        status = "";
        discountOrStarsEarned = 0;
        email = "";
        mobile = "";
    }

    //Argument Constructor 
    public CustomerV2(String firstName, String lastName, String rewardNumAndStatus,String statusIn, double discountOrStarsEarned, String email, String mobile){
        this.firstName = firstName;
        this.lastName = lastName;
        this.rewardNum = rewardNumAndStatus;
        this.status = statusIn;
        this.discountOrStarsEarned = discountOrStarsEarned;
        this.email = email;
        this.mobile = mobile;
    }

    //Object Constructor
    public CustomerV2(CustomerV2 c){
        setFirstName(c.getFirstName());
        setLastName(c.getLastName());
        setRewardNumAndStatus(c.getRewardNumAndStatus());
        setStatus(c.getStatus());
        setDiscountOrStarsEarned(c.getDiscountOrStarsEarned());
        setEmail(c.getEmail());
        setMobile(c.getMobile());
    }

    //---Mutator Methods for Customer---

    //setFirstName() sets the value of firstName
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    //setLastName() sets the value of lastName
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //setRewardNumAndStatus sets the value for Customer Reward Num & Membership Status
    public void setRewardNumAndStatus(String rewardNumAndStatus){
        this.rewardNum = rewardNumAndStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //setDiscountOrStarsEarned sets the value for Customer Discount or how many Stars earned
    public void setDiscountOrStarsEarned(double discountOrStarsEarned){
        this.discountOrStarsEarned = discountOrStarsEarned;
    }

    //setEmail sets the email address of the Customer
    public void setEmail(String email){
        this.email = email;
    }

    //setMobile sets the phone number of the Customer
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    //---Accessor Methods for Customer---
    //getFirstName returns Customer's first name
    public String getFirstName(){
        return this.firstName;
    }
    
    //getLastName returns Customer's last name
    public String getLastName(){
        return this.lastName;
    }

    //getRewardNumAndStatus returns Customer's Reward Number and Membership Status
    public String getRewardNumAndStatus(){
        return this.rewardNum;
    }

    public String getStatus() {
        return this.status;
    }

    //getDiscountOrStarsEarned returns Customer's discount or stars earned
    public double getDiscountOrStarsEarned(){
        return this.discountOrStarsEarned;
    }

    //getEmail returns Cusomter's email address
    public String getEmail(){
        return this.email;
    }

    //getMobile returns Customer's phone number
    public String getMobile(){
        return this.mobile;
    }

    //toString() prints all Customer information
    public String toString(){
        return "First Name: " + this.getFirstName() + "\n" 
        + "Last Name: "  + this.getLastName()  + "\n" 
        + "Reward: " + this.getRewardNumAndStatus() + "\n"
        + "Status: " + this.getStatus() + "\n" 
        + "Email: " + this.getEmail() + "\n" 
        + "Discount: " + this.getDiscountOrStarsEarned() + "\n" 
        + "Mobile: " + this.getMobile() + "\n";  
    }

    // adds customer to customer array
    //* used in controller class
    public void placeInArray(CustomerV2 newCustomer) {
        customerArray[count] = newCustomer;
        count++;
    }

    //adding new cusomter information into the "customer.txt" File
    public void updateFile(){
        try{
            FileWriter append = new FileWriter("customers.txt", true); //append not override

            append.write(this.firstName + "\n" + this.lastName + "\n" + this.rewardNum + "\n" + this.status + "\n" + this.discountOrStarsEarned + "\n" + this.email + "\n" + this.mobile);
            append.flush();
            append.close(); //close FileWriter
        }
        catch(IOException e){
            e.printStackTrace(); //print out error
        }
    }

    // returns the last customer's reward number in the array +1
    //* used in controller class
    //TODO if the first customer in the array is registering the number needs to be random
    // ! NOT CURRENTLY BEING USED
    public String addCustomerNum() {
        String newNum = customerArray[count].rewardNum;
        newNum += 1;
        return newNum;
    }
}
