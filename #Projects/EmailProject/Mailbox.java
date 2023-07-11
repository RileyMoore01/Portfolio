/*
Author: Riley Moore
FileName: Mailbox.java
Specification: This program will read in the inputFile text and store them accordingly to the Employee obejct
For: CS 2365 Object Oriented Programming Section 001
*/
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mailbox{
    private Employee employees[];
    private int n, errors;          //n is the num of employees
    private String inputFile;

  public static final Map<String, String> errorMap = new HashMap<>(); // Static map for errors

  static {
    errorMap.put("0", "No errors encountered");
    errorMap.put("1", "Fewer employees than expected");
    errorMap.put("2", "More employees than expected");
  }

  private static const int deafultNumEmp = 10;  //Static constant for deault size of array

//Deault constructor
  public Mailbox(){
    this.employees = new Employee[deafultNumEmp];
    this.inputFile = "newHire.txt";
  }

//2-argument constructor
  public Mailbox(int numEmp, String inputFile){
    this.employees = new Employee[defaultNumEmp];
    this.inputFile = inputFile;
  }

//Mutator Functions
//Setting an employee into a location i in the array
  public void setEmployeeLocation(Employee employyees){
    for(int i = 0; i < n; i++){
      this.employees[i] = this.employees[i];
    }
  }

//Accessor method that safley returns an employee object
  public Employee objectLocation(Employee employees){
     return this.employees[i];
  }

  //Method to return number of employee objects
  public static int getLocation(int i){
    
  }

  //Method to read in the input file and store in employees[i]
  //I was not able to finish this part of this proejct
  //I had two methods I was working through as the deadline restricted me from finishing my methodology

  //Method 1: Use try & catch to read in the 4 argumetns on each line
  public void readInput(String inputFile){
    try {
      inputFile = new Scanner(new FileInputStream(this.filename));
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
      System.exit(0);
    }

    for(int i = 0; i < n; i++){
      for(int j = 0; j < 4; j++){
        this.temp_Array[i][j] = inputFile.next();
      }
      if(inputFile.hasNextLine()){
        inputFile.nextLine();
      }
    }
    inputFile.close();
    
    //-------------------------------------------------------

    //Method 2: While inputFile has a nextLine. Read in the 4 arguments accordingly to fit the Employee object
    File file = new file("newHire.txt");
    Scanner scanner = new Scanner(file);
    String breakIndex;
    while(scanner.hasNextLine()){
      file = scanner.nextLine();
      breakIndex = file.indexOf(" ");
      getFirstName(this.firstName) = inputFile.substring(0, breakIndex);
      inputFile = file.substring(breakIndex + 1);
      breakIndex = file.indexOf(" ");
      getLastName(this.lastName) = file.substring(0, breakIndex);
      inputFile = file.substring(breakIndex + 1);
      breakIndex = file.indexOf(" ");
      getDepartment(this.department) = file.substring(0, breakIndex);
      inputFile = file.substring(breakIndex + 1);
      breakIndex = file.indexOf(" ");
      getAltEmail(this.altEmail) = file.substring(breakIndex+1);
    }
  }
}
