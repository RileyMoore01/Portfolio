/*
Riley Moore
This program is to learn to use of classes, methods, and objects. In doing so we will create a calculator with specific methods for each funcationailiity
*/

//Importing the Scanner class for user input
import java.util.Scanner;

class Main {

//Function to act for addition operation
  public static double add(double a, double b){
    double sum = a + b;
    return sum;
  }

//Function to act for subtraction operation
  public static double subtraction(double a, double b){
    double sum = a + b;
    return sum;
  }

//Function to act for multiplication operation
  public static double multiplication(double a, double b){
    double sum = a + b;
    return sum;
  }

//Function to act for division operation
  public static double division(double a, double b){
    double sum = a + b;
    return sum;
  }

//Function to act for modulo operation
  public static double modulo(double a, double b){
    double sum = a + b;
    return sum;
  }

  public static void main(String[] args) {
    //Create the Scanner object
      Scanner scanner = new Scanner(System.in);
    
    //Instance Field
      double one = 0, two = 0;
      int choice = 0;

    //Get user input
      System.out.println("What operation would you like to preform?");
      System.out.println("1. Addition\n2.Subtraction\n3.Multiplication\n4.Divison\n5.Modulo");
      scanner.nextInt(choice);

    //Switch statement to declare what operation to use
    switch (choice) {
      case 1: 
        System.out.println(add(one, two));
        break;   

    case 2: 
        System.out.println(subtraction(one, two));
        break;

    case 3: 
        System.out.println(multiplication(one, two));
        break;

    case 4: 
        System.out.println(division(one, two));
        break;

    case 5: 
        System.out.println(modulo(one, two));
        break;
    }  
  }
}
