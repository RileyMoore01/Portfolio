/*
    NAME: Customer
    PURPOSE: The Customer Class contains the first name, last name, type, discount, emai, and phonenumber
    INVARIANT: Abstract class to use subclasses for creating customer records & use inheritance to execute the different type of custoemrs
*/
abstract class Customer {
    
    //Customer details for accounts
    private String firstName;
    private String lastName;
    private String type;
    private String discount;
    private String email;
    private String phoneNumber;

    /* Mutator and Accesor methods for First Name
        NAME: getFirstName
        PURPOSE: Return the firstName String value (Accessor Method)
        PRECONDITION: firstName must be initialized
        POSTCONDITION: The method calling getFirstName should be returned firstName: String
    */
    public String getFirstName(){
        return firstName;
    }

    /* Mutator and Accesor methods for Last Name
        NAME: getLastName
        Parameters: String firstname
        PURPOSE: Return the firstName String value (Mutator Method)
        PRECONDITION: firstName must be initialized
        POSTCONDITION: The method calling getFirstName should reveieve a mutated firstName
    */
    public String setFirstName(String firstName){
        this.firstName = firstName;
        return firstName;
    }

    //Mutator and Accesor methods for Last Name
    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //Mutator and Accesor methods for Type
    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    //Mutator and Accesor methods for Email
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    //Mutator and Accesor methods for Discount
    public String getDiscount(){
        return discount;
    }

    public void setDiscount(String discount){
        this.discount = discount;
    }


    //Mutator and Accesor methods for Phone Number
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumner){
        this.phoneNumber = phoneNumber;
    }

    /* 
        NAME: toString
        PURPOSE: Returns an appended string of first name, last name, type, email, discount, and phoen number
        PRECONDITION: First & Last, Type, Email, Discount, and Phonenumber must have accessor methods and be intialized
        POSTCONDITION: The method calling toSring should be returned the appened string
    */
    public String toString(){
        return "First Name: " + this.getFirstName() + "\n" 
        + "Last Name: "  + this.getLastName()  + "\n" 
        + "Customer Type: " + this.getType() + "\n" 
        + "Email: " + this.getEmail() + "\n" 
        + "Discount: " + this.getDiscount() + "\n" 
        + "Mobile: " + this.getPhoneNumber() + "\n";  
    }

    //Default constructor
    Customer() {
        firstName = "";
        lastName = "";
        type = "";
        discount = "";
        email = "";
        phoneNumber = "";

    }

    //Customer constructor method
    //Sets all the instance variables in Customer
    Customer (String firstName, String lastName, String type, String email, String discount, String phoneNumber){
        setFirstName(firstName);
        setLastName(lastName);
        setType(type);
        setDiscount(discount);
        setEmail(email);
        setDiscount(discount);
        setPhoneNumber(phoneNumber);
    }
    public static void main(String[] args){

    }
}

/*
    NAME: Elderly
    PURPOSE: Calculate the best travel plan pass
    INVARIANT: return best plan
*/

class Elderly extends Customer {
    
    private String birthYear;       //Storing when the customer was born

    //Conditional values for weekly and monthly pass
    private static final double monthPass = 80.0;
    private static final double weekPass = 40.0;

    //Rates for the Elderly
    private static final double weekDayElderly = 1.80;
    private static final double weekEndElderly = 1.00;
    
    Elderly () {
        super();
        setBirthYear(birthYear);
    }

    /*
        NAME: getElderlyTripInfo
        Parameters: weekEnd, weekDay to track number of trips, and month to track number of weeks in that month
        PURPOSE: Calculate the best pass for the customers travel plan
        PRECONDITION: Customer orders must be read in
        POSTCONDITION: The best pass plan for the customer is returned
    */
    public String getElderlyTripInfo (double weekEnd, double weekDay, double month) {
    
        String ecoPlanE = null;         //Final economical plan for customer
        double total = 0.0;             //Final total of customers trip costs

    //Algorithm to calculate customers total cost

        //Accounting for the $10 max per day on weekdays
        if(weekDay > 10){
            weekDay = 10 * 5;
        } 
        else {
            weekDay = weekDay * weekDayElderly * 5;
        }


        //Accounting for the $10 max per day on weekends
        if(weekEnd > 8){
            weekEnd = 10 * 2;
        } 
        else {
            weekEnd = weekEnd * weekEndElderly * 2;
        }

        //Finding the customers total
        total = weekEnd + weekDay;
        total *= month;

    //Seeing what plan is best for the customer

        //If total is greater than month pass, month pass would be useful
        if(total >= monthPass){
            ecoPlanE = "Monthly pass for $80.";
        }

        //If total is greater than week pass buy the week pass
        else if (total >= weekPass) {
            ecoPlanE = "Weekly pass for $40.";
        }

        //If total is less than week pass use PAYG card
        else if (total < weekPass) {
            ecoPlanE = "PAYG Card.";
        }

        //Otherwise custoemr not found
        else {
            System.out.println("***Error: " + getFirstName() + getPhoneNumber() + " has no matching customer record");
        }

        return ecoPlanE;
    }

    //Mutator and Accessor Methods for birthYear
    public String getBirthYear(){
        return birthYear;
    }

    public void setBirthYear(String birthYear){
        this.birthYear = birthYear;
    }
}
