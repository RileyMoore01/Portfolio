import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/*
    NAME: CustomerArray.java
    PURPOSE: Read in the newHires file, sort for customer type, read in customer orders and assign best deal accordingly, finally print out all the customer records
    INVARIANT: Once main calls this class, the customer records will be created and displayed to the new file
*/
public class CustomerArray {
    //Final customer array
    Customer[] customers;

    //Varibles to store recommended pass to customers
    private String regular = null;
    private String student = null;
    private String military = null;
    private String elderly = null;

    int customerNum = 0;        //Number of customers in the file

    //Defualt  Constructor
    public CustomerArray () {
        this.customers = new Customer[0];
    }

    //Constrcutor w n-argumetns
    CustomerArray (int i) {
        this.customers = new Customer[i];
    }

//Method to read in the file and store into Customers information array
/*
    NAME: readFile
    PURPOSE: Read in the customers information and store in the Custoemr array
    PRECONDITION: Customer array must be intialized
    POSTCONDITION: Customer records stored in Customer array
*/
    public void readFile () {

        //Creating a file for newhires
        File file = new File("newhires.txt");
        Scanner scan = null;

        //Error checking
        if(customers == null){
            System.out.println("There is no customer in the array");
        } 
        else {
            try {
                //Open and read in the file arguments
                scan = new Scanner(file);

                int i = 0;      //Tracks the index of customers[]   

                //While loop to check if file has a readable next line
                while(scan.hasNextLine()) {
                //Temp varaibles
                    String firstName = scan.nextLine();
                    String lastName = scan.nextLine();
                    String type = scan.nextLine();
                    String discount = scan.nextLine();
                    String email = scan.nextLine();
                    String phone = scan.nextLine();  

                //Storing temp vars in final array
                    customers[i].setFirstName(firstName);
                    customers[i].setLastName(lastName);
                    customers[i].setType(type);
                    customers[i].setDiscount(discount);
                    customers[i].setEmail(email);
                    customers[i].setPhoneNumber(phone);

                    i++;
                }
            }

            //Incase file can not be found or is null
            catch (FileNotFoundException e) {
                System.out.println("Error: File Not Found");
                System.exit(0);
            }
        }

        scan.close();
    }
    

//Method to process the customer orders
/*
    NAME: readOrders
    PURPOSE: Read in customers orders and call upon inherited subclasses of Customer according to type to calculat best plan
    PRECONDITION: Must have a new orders file
    POSTCONDITION: The best travel plan pass should be appende to that customers arary
*/
    public void readOrders () {
        
        //Creating the file object and scanner for orders.txt
        File file = new File("Orders.txt");
        Scanner scan = null;

        //Error checking
        if(customers == null){
            System.out.println("There is no customer in the array");
        }
        else {
            try {
                //Open and read in the file arguments
                scan = new Scanner(file);
                customerNum = scan.nextInt();
                scan.nextLine();    //reads in the  next line
                
                //Scanning in the arguments from orders.txt
                for(int i = 0; i < customerNum; i++){
                    String lastName = scan.next();
                    String phoneNumber = scan.next();
                    int weekDayTrip = scan.nextInt();
                    int weekEndTrip = scan.nextInt();
                    int weekTrip = scan.nextInt();
                    scan.nextLine();
                
                    //check to see if phone number & last name of customer is correct
                    //If correct, placed in the correct customer Type group
                    if((lastName.equals(customers[i].getLastName())) && (phoneNumber.equals(customers[i].getPhoneNumber()))){
                        
                        if(customers[i].getType().equals("Regular")){
                            Regular temp = new Regular();
                            regular = temp.getRegularTripInfo(weekDayTrip, weekEndTrip, weekTrip);
                        }

                        else if(customers[i].getType().equals("Student")){
                            Student temp = new Student();
                            student = temp.getStudentTripInfo(weekDayTrip, weekEndTrip, weekTrip);
                        } 

                        else if(customers[i].getType().equals("Military")){
                            Military temp = new Military();
                            military = temp.getMilitaryTripInfo(weekDayTrip, weekEndTrip, weekTrip);
                        }

                        else if(customers[i].getType().equals("Senior")){
                            Elderly temp = new Elderly();
                            elderly = temp.getElderlyTripInfo(weekDayTrip, weekEndTrip, weekTrip);
                        }

                        else{
                        System.out.println("Invalid Customer Type");
                        }
                    } 
                    else{
                        System.out.println("Error Wrong Last Name or Mobile Number");
                    }
                }
            }
                    
            catch (FileNotFoundException e) {
                System.out.println("Error: File Not Found");
                System.exit(0);
            }
        }

        scan.close();
    }

//Accessor methods for type Regular
    public String getRegular(){
        return regular;
    }

//Accessor method for type Student
    public String getStudent(){
        return student;
    }

//Accessor method for type Military
    public String getMilitary(){
        return military;
    }

//Accessor method for type Elderly
    public String getElderly(){
        return elderly;
    }

//Accessor for number of customers in the file
    public int getCustomerNumber () {
        return customerNum;
    }




    //Method to Print out the customer information in main 
    /*
    NAME: printCustomers
    PURPOSE: Write the customer records and best pass to a new text file
    PRECONDITION: Customer array must have records and travel plan
    POSTCONDITION: New file with records and travel plan all listdd
*/
    public void printCustomers () {
        try {
            File file2 = new File("customerOrders.txt");
            PrintWriter writer = new PrintWriter(file2);
            

            for(int i = 0; i < customerNum; i++){
                if(customers[i].getType().equals("Regular")){
                    customers[i].toString();
                    writer.append(regular);
                }

                if(customers[i].getType().equals("Student")){
                    customers[i].toString();
                    writer.append(student);
                }

                if(customers[i].getType().equals("Military")){
                    customers[i].toString();
                    writer.append(military);
                }

                if(customers[i].getType().equals("Elderly")){
                    customers[i].toString();
                    writer.append(elderly);
                }
            }
            writer.close();
        }


        catch (FileNotFoundException e) {
            System.out.println("Error: File Not Found");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        
    }
}
