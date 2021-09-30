/*
Author: Riley Moore
FileName: Main.java
Specification: This program is executing the Mailbox and Employee class previously created
For: CS 2365 Object Oriented Programming Section 001
*/

/* NAME: Main
    PARAMETERS: String[] args 
    PURPOSE: Create the Mailbox instance and use the Employee class to safley set the employee information
    PRECONDITION: Mailbox and Employee classes must be defied
    POSTCOndition: New Employee objects with detailed information will be assigned as well as the Mailbox class functionally working
  */
public class Main {
  public static void main(String[] args){
    Mailbox mailbox = new Mailbox(10,"newHire.txt");
    mailbox.toString();
  }
}
