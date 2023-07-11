/*
    AUTHOR: Riley Moore
    FILENAME: Main.java
    SPECIFICATION: Reading in customer records, reading in file orders, and finally determining the best plan for the customer
    FOR: CS 2365 Object-Oriented Programming Section 01
*/

/*
    NAME: Main.java
    PURPOSE: Create CustomerArray, call readFile, call readOrders, call printCustomers()
    INVARIANT: Main method instansiates runs all the existing classes 
*/
class main {
    /* 
        NAME: main
        PURPOSE: call the two read and print methods in CustomerArray.java
        PRECONDITION: CustomerArray must have the correct methods readFile(), readOrders(), and printCustomers()
        POSTCONDITION: The customer records and best recommened plan shohuld be written to a new file
    */
    public static void main(String[] args){
        
        //Creating the CustomerArray object to access its methods
        CustomerArray customer = new CustomerArray(10);

        //Reading in the new hires file
        customer.readFile();

        //Reading in the new orders file
        customer.readOrders();

        //Printing the final customer records
        customer.printCustomers();
    }
}
