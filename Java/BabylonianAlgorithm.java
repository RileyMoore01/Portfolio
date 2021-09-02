/*
This program takes a keyboard integer input, runs its through
the Babylonian algorithm i number of times. Outputs the result. 
Riley Moore - ObjectOrientedProgramming
*/
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //Instance Field
    double n, r, guess;

    //Creating the scanner object
    Scanner scanner = new Scanner(System.in);

    //Getting user input
    System.out.printf("What number would you like to use?");
    n = scanner.nextDouble();

    //loop for Babylonian algorithm to run i times
    guess = n / 2;
    for (int i = 0; i < 5; i++){
      r = n / guess;
      guess = (guess + r) / 2;
    }

    //Print result to user
    System.out.print("The guess for the the square root of " + n);
		System.out.printf(" is %.2f.%n", n, guess);
    scanner.close();
  }
}
