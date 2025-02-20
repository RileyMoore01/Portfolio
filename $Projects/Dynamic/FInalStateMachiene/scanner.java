package Leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class scanner {



  static class scanner2 {

    private boolean error;

    public void setError(boolean newError){
      this.error = newError;
    }

    public boolean getError() {
      return error;
    }

    public String[] scan(File file) throws IOException {

      //Read file in and make it readable character by character
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);

      // "prep" the first character into current int
      int currentInt = br.read(); //integer representation of current character
      int nextInt = 0; //integer representation of next character
      int count = 0;  //counts how many tokens have been returned
      String[] output = new String[18];

      while (nextInt != -1) {  // until end of the array

        if (nextInt != 0) {
          currentInt = nextInt;
        }

        nextInt = br.read(); //peek next character

        char currentChar = (char) currentInt; // set current character
        char nextChar = (char) nextInt;

        if (currentChar == ' ' | currentInt == 10 | currentInt == 13) { // skips whitespace and new line (ASCII Code)
          continue;
        }

        //get symbols as tokens
        if (currentChar == '(') {
          count = storeToken(output, count, "lpar");
        } else if (currentChar == ')') {
          count = storeToken(output, count, "rpar");
        } else if (currentChar == '+') {
          count = storeToken(output, count, "plus");
        } else if (currentChar == '-') {
          count = storeToken(output, count, "minus");
        } else if (currentChar == '*') {
          count = storeToken(output, count, "times");
        }

        // if assign token return assign, if not return error (increment r to next char)
        else if (currentChar == ':' && nextChar == '=') {
          nextInt = br.read();
          count = storeToken(output, count, "assign");
        }


        //if it is a comment skip till not a comment
        else if (currentChar == '/') {

          if (nextChar == '*' | nextChar == '/') {

            nextInt = br.read();
            nextChar = (char) nextInt;

            while (currentChar != '*' && nextChar != '/') {

              currentInt = nextInt;
              currentChar = (char) currentInt;
              nextInt = br.read();
              nextChar = (char) nextInt;

            }

          }

        }
        else if (Character.isDigit(currentChar) | currentChar == '.') { // if character is digit start loop
          int decimalCount = 0;
          while ((Character.isDigit(currentChar)) | currentChar == '.') { // loop to find decimal or return number

            currentInt = nextInt;
            currentChar = (char) currentInt;
            nextInt = br.read();

            if (currentChar == '.') {
              decimalCount++;
            }
          }

          if (decimalCount > 1) { // if there is more than one decimal error out
            setError(true);
          }

          count = storeToken(output, count, "number");


        }

        //finds id token types
        else if (Character.isLetter(currentChar)) {

          int j = 0;
          char[] token = new char[16];

          while (Character.isLetter(currentChar) | Character.isDigit(currentChar)) { // loops till the next character is not
            // a digit or letter
            token[j] = currentChar;

            currentInt = nextInt;
            currentChar = (char) currentInt;

            nextInt = br.read();

            j++;
          }

          token = Arrays.copyOf(token, j); // makes a correct array size

          String string = new String(token);

          // stores either read, write, or id depending on the token
          if (string.equals("write")) {
            count = storeToken(output, count, "write");
          } else if (string.equals("read")) {
            count = storeToken(output, count, "read");
          } else {
            count = storeToken(output, count, "id");
          }
        }

        //set error token if it does not match any other tokens
        else {

          setError(true);

        }


      }

      output[count] = "0";
      return output;

    }

    private int storeToken(String[] output, int count, String lpar) { //stores token and returns the count
      output[count] = lpar;
      count++;
      return count;
    }

  }



  public static void main(String[] args) throws IOException {

    //initialize variables
    if(args.length == 0){
      System.err.print("\nNo input file\n\nexample:\n\njava scanner.java fileName\n");
      System.exit(1);
    }

    File myObj = new File(args[0]);
    scanner2 scanner2 = new scanner2();
    String[] output;

    //set output to an array of tokens
    output = scanner2.scan(myObj);

    //if the scanner returns an error end program, else print tokens
    if (scanner2.getError()){

      System.out.println("error");

    }

    else {

      printTokens(output);

    }

  }

  private static void printTokens(String[] output) {

    int i;

    System.out.print("(");

    for (i = 0; i < output.length; i++) {

      if (output[i + 1].equals("0")) {
        System.out.print(output[i]);
        break;
      }

      System.out.print(output[i] + ", ");

    }

    System.out.print(")");
  }

}
