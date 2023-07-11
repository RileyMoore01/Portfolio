/*
Author: Riley Moore
FileName: Email.java
Specification: This program will create new customer portfolios containting
For: CS 2365 Object Oriented Programming Section 001
*/
import java.security.SecureRandom;

public class Employee {
  //Creating the private variables to store new employee information
    private String firstName;
    private String lastName;
    private String department;
    private String compEmail;
    private String altEmail;
    private String emailPassword;

  //Default constuctors assigned 
    public Employee() {
      this.firstName = "FIRSTNAME";
      this.lastName = "LASTNAME";
      this.department = "DEPARTMENT";
      this.compEmail = "FIRSTNAME.LASTNAME@DEPARTMENT.COMPANY.COM";
      this.altEmail = "NA";
      this.emailPassword = "PASSWORD";
    }

  /* NAME: Employee
    PARAMETERS: NA
    PURPOSE: Return the value of first name
    PRECONDITION: fName should be passed into the mutator method
    POSTCONDITION: The fname variable should be returned*/
    public Employee(String firstName, String lastName, String depart,  String alterEmail) {
      this.getFirstName(firstName);
      this.getLastName(lastName);
      this.getDepartment(department);
      this.getAltEmail(altEmail);
      this.getCompanyEmail();
      this.getEmailPassword();
    }

    /* NAME: Employee
    PARAMETERS: NA
    PURPOSE: Return the value of first name
    PRECONDITION: fName should be passed into the mutator method
    POSTCONDITION: The fname variable should be returned*/
    public Employee(Employee employee) {
      this.getFirstName(employee.firstName);
      this.getLastName(employee.lastName);
      this.getDepartment(employee.department);
      this.getCompanyEmail(employee.compEmail);
      this.getAltEmail(employee.altEmail);
      this.getEmailPassword(employee.emailPassword);
    }

      /* NAME: getFirstName
    PARAMETERS: firstName 
    PURPOSE: Mutator Method to set priavte variabels
    PRECONDITION: Employee object must exist
    POSTCOndition:Employee object will be set to firstname
  */public void getFirstName(String firstName){
      this.firstName = firstName;
    }

    public void getLastName(String lastName){
      this.lastName = lastName;
    }

    public void getDepartment(String department){
      this.department = department;
    }

    public void getCompanyEmail() {
      this.compEmail = this.firstName + "." + lastName + "@" + this.department + ".company.com";
    }
    public void getCompanyEmail(String compEmail){
      this.compEmail = compEmail;
    }

    public void getAltEmail(String altEmail){
      if(altEmail != null){
        this.altEmail = altEmail;
      } else
          this.altEmail = "INVALID";
    }

    public void getEmailPassword(){
      int len = 10;
      this.emailPassword = generatePassword(len);
    }

    public void getEmailPassword(String emailPassword){
      this.emailPassword = emailPassword;
    }

  /*Name: toString
  Purpose: to write the Employee information to console
  Parameters
  */
  public String toString(){
    String text = "  Name:    "+firstName+" "+lastName+"\n";
    text += "  Department:    "+department+"\n";
    text+= "  Company Email:    "+compEmail+"\n";
    text+= "  Alternate Email:    "+altEmail+"\n";
    text+= "  Password:   "+emailPassword+"\n";
    
    return text;
  }

  /* NAME: gemeratePassword
    PARAMETERS: len - the length allowed for the password that wil be created  
    PURPOSE: Create a randomly generated password for the employee
    PRECONDITION: Must pass in the password length allowed - len
    POSTCOndition: emailPassword = new randomPassword
  */
  public String generatePassword(int len){
    final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$_%^&-+=:;<>?/";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++){
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
  }
}
