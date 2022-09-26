//imports for Files, and exception handling
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;
import java.text.*;

//CustomerFile class is to have an object of the Customer Class to 
public class CustomerFileV2{
    int numCus; //number of customers
    CustomerV2[] cus; //customer object array

    //default constructor
    //default amount of customers is 10
    public CustomerFileV2(){
        this.cus = new CustomerV2[10];
    }

    //argument constructor
    //arugment for number of customers
    public CustomerFileV2(int numCus){
        this.cus = new CustomerV2[numCus];
    }

    //scanFile() reads in customer.txt and sets the input into corresponding values
    public void scanFile() {
        //Scanner object readIn
        Scanner readIn = null;

        //check to see if the Customer array does NOT exists
        if(cus == null){
            System.out.println("There is no Customer");
        }
        else{
            try{
                //readIn is a Scanner Object to input file customer.txt
                readIn = new Scanner(new FileInputStream("customers.txt"));

                //reading in number of Customers and setting value
                int num = readIn.nextInt();
                this.numCus = num;
                readIn.nextLine();
                cus = new CustomerV2[num];
                
                //while loop reads in all information until there is no line to read
                //and sets customer information
                int i = 0;
                while(readIn.hasNextLine()){
                    CustomerV2 temp = new CustomerV2();
                    temp.firstName= readIn.nextLine();
                    temp.lastName = readIn.nextLine();
                    String notThis = readIn.nextLine();
                    String[] arrStr = notThis.split(" ",2);
                    temp.rewardNum= arrStr[0];
                    temp.status = arrStr[1];
                    temp.discountOrStarsEarned = readIn.nextDouble();
                    readIn.nextLine();
                    temp.email = readIn.nextLine();
                    temp.mobile= readIn.nextLine();
                    
                    cus[i] = temp;
                    i++;
                }
            }
            catch(Exception e){
                //prints error message and cause
                System.out.println("ERROR: " + e.getCause());
            }
        }
        readIn.close(); //close scanner object
    }

    //compareLogin() is a boolean function to compare customer's last name and reward
    //to customer information. return TRUE if matche & FALSE if does NOT match
    public boolean compareLogin(String lastNameInput, String rewardNumInput) {
        //loop to go through every customer last name and reward until a match is found
        //if a match is not found then returns false
        scanFile();
        for(int i = 0; i < numCus; i++){
            if((cus[i].getLastName().equals(lastNameInput)) && (cus[i].getRewardNumAndStatus().equals(rewardNumInput))){
                return true;
            }
        }
        return false;
    }

    //prints out all values in the Customer object array
    //toString() for CustomerFile class
    public void printCustomerInfo(){
        //loop prints customer info
        for(int i = 0; i < numCus; i++){
            cus[i].toString();
        }
    }
}
