package Leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class scanner {

  private boolean error;

  terminalToken id;
  terminalToken number;
  terminalToken read;
  terminalToken write;
  terminalToken assign;
  terminalToken lpar;
  terminalToken rpar;
  terminalToken minus;
  terminalToken plus;
  terminalToken times;
  terminalToken endSymbol;

  public void setError(boolean newError) {
    this.error = newError;
  }

  public boolean getError() {
    return error;
  }

  public terminalToken[] scan(File file) throws IOException {

    //Read file in and make it readable character by character
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    // "prep" the first character into current int
    int currentInt = br.read(); //integer representation of current character
    int nextInt = 0; //integer representation of next character
    int count = 0;  //counts how many tokens have been returned
    terminalToken[] output = new terminalToken[18];

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
        lpar = new terminalToken("(");
        lpar.setName("lpar");
        count = storeToken(output, count, lpar);
      } else if (currentChar == ')') {
        rpar = new terminalToken(")");
        rpar.setName("rpar");
        count = storeToken(output, count, rpar);
      } else if (currentChar == '+') {
        plus = new terminalToken("+");
        plus.setName("plus");
        count = storeToken(output, count, plus);
      } else if (currentChar == '-') {
        minus = new terminalToken("-");
        minus.setName("minus");
        count = storeToken(output, count, minus);
      } else if (currentChar == '*') {
        times = new terminalToken("*");
        times.setName("times");
        count = storeToken(output, count, times);
      }

      // if assign token return assign, if not return error (increment r to next char)
      else if (currentChar == ':' && nextChar == '=') {
        nextInt = br.read();
        assign = new terminalToken(":=");
        assign.setName("assign");
        count = storeToken(output, count, assign);
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

      } else if (Character.isDigit(currentChar) | currentChar == '.') { // if character is digit start loop

        int decimalCount = 0;
        char[] numArr = new char[16];
        int i = 0;

        while ((Character.isDigit(currentChar)) | currentChar == '.') { // loop to find decimal or return number

          numArr[i] = currentChar;
          currentInt = nextInt;
          currentChar = (char) currentInt;
          nextInt = br.read();

          i++;

          if (currentChar == '.') {
            decimalCount++;
          }
        }

        if (decimalCount > 1) { // if there is more than one decimal error out
          setError(true);
        }

        numArr = Arrays.copyOf(numArr, i);
        String completedNumber = new String(numArr);
        number = new terminalToken(completedNumber);
        number.setName("number");
        count = storeToken(output, count, number);


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
          write = new terminalToken("write");
          write.setName("write");
          count = storeToken(output, count, write);
        } else if (string.equals("read")) {
          read = new terminalToken("read");
          read.setName("read");
          count = storeToken(output, count, read);
        } else {
          id = new terminalToken(string);
          id.setName("id");
          count = storeToken(output, count, id);
        }
      }

      //set error token if it does not match any other tokens
      else {

        setError(true);

      }


    }

    terminalToken endMarker = new terminalToken("0");
    output[count] = endMarker;
    return output;

  }

  private int storeToken(terminalToken[] output, int count, terminalToken token) { //stores token and returns the count
    output[count] = token;
    count++;
    return count;
  }


  public static void main(String[] args) throws IOException {

    //initialize variables
    if(args.length == 0){
      System.err.print("\nNo input file\n\nexample:\n\njava scanner.java fileName\n");
      System.exit(1);
    }

    File myObj = new File(args[0]);
    scanner scanner = new scanner();
    terminalToken[] output;

    //set output to an array of tokens
    output = scanner.scan(myObj);

    //if the scanner returns an error end program, else print tokens
    if (scanner.getError()){

      System.out.println("error");

    }

    else {

      printTokens(output);
      parser parser = new parser(output);

    }

  }

  private static void printTokens(terminalToken[] output) {

    int i;



    System.out.print("(");

    for (i = 0; i < output.length; i++) {

      if (output[i + 1].getValue().equals("0")) {
        output[i+1].setValue("$$");
        output[i+1].setName("$$");
        System.out.print(output[i].getName() + ", ");
        System.out.print(output[i+1].getValue());
        break;
      }

      System.out.print(output[i].getName() + ", ");

    }

    System.out.println(")");
  }

}

class parser {

  // assigns all token types
  String program     = "Program";
  String stmt_list   = "stmt_list";
  String stmt        = "stmt";
  String factor_tail = "factor_tail";
  String factor      = "factor";
  String expr        = "expr";
  String term_tail   = "term_tail";
  String term        = "term";
  String id          = "id";
  String read        = "read";
  String write       = "write";
  String assign      = "assign";
  String number      = "number";
  String lpar        = "lpar";
  String rpar        = "rpar";
  String minus       = "minus";
  String plus        = "plus";
  String times       = "times";
  String endSymbol   = "$$";
  String epsilonProduction = "\u03B5";

  // array for the output of scan
  terminalToken[] output;
  int count = 0;  //increments output array
  int spaces = 0; //indentation increment
  terminalToken token;   //matching token

  // construct to start the routine
  parser(terminalToken[] output) {
    this.output = output;
    token = this.output[count];
    program(); //starts sequence
  }

  public void setCount(int count) {
    this.count = count;
  }

  // prints the leading token ex: <Program> including indentation
  private void printIndentEntry(String string){
    string = "<" + string + ">";
    for (int i = 0; i < spaces; i++){
      string = " " + string;
    }
    spaces += 1;
    System.out.println(string);
  }

  private void printMatch(String string){
    if (token.getValue().equals("$$")){
      for (int i = 0; i < spaces+3; i++){
        string = " " + string;
      }
      System.out.println(string);
    } else {
      string = token.getValue();
      for (int i = 0; i < spaces + 3; i++) {
        string = " " + string;
      }
      System.out.println(string);
    }
  }

  // script to print indents
  private void printScript(String string) {
    printIndentEntry(string);
    match(string);
    printIndentExit(string);
  }

  // prints the exiting token ex: </Program> including indentation
  private void printIndentExit(String string){
    spaces -= 1;
    string = "</" + string + ">";
    for (int i = 0; i < spaces; i++){
      string = " " + string;
    }
    System.out.println(string);
  }

  // error function
  private void parseError() {
  }

  // detects if the input matches the rules of a programs
  private void match (String expectedToken) {
    if (token.getName().equals(expectedToken)) {
      printMatch(expectedToken);
      setCount(count + 1);
      token = output[count];
    }
  }

  private void program () {

    printIndentEntry(program);

    if(token.getName().equals(endSymbol) || token.getName().equals(id) || token.getName().equals(read) || token.getName().equals(write)) {

      stmt_list();
      printIndentExit(program);
      //match(endSymbol);

    } else parseError();

  }

  private void stmt_list () {
    if(token.getName().equals(id) || token.getName().equals(read) || token.getName().equals(write)) {
      printIndentEntry(stmt_list);
      stmt();
      stmt_list();
      printIndentExit(stmt_list);
    }else if (token.getName().equals(endSymbol)){
      printMatch("\u03B5");
    }else parseError();
  }

  private void stmt () {

    printIndentEntry(stmt);

    if(token.getName().equals(id)){

      printScript(id);
      printScript(assign);
      expr();
      printIndentExit(stmt);

    }
    else if(token.getName().equals(read)){

      printScript(read);
      printScript(id);
      printIndentExit(stmt);

    }
    else if (token.getName().equals(write)){

      match(write);
      expr();
      printIndentExit(stmt);

    } else parseError();
  }

  private void expr () {
    printIndentEntry(expr);
    if(token.getName().equals(id) || token.getName().equals(number) || token.getName().equals(lpar) ) {

      term();
      term_tail();
      printIndentExit(expr);

    }else parseError();
  }

  private void term_tail () {
    printIndentEntry(term_tail);
    if (token.getName().equals(plus) || token.getName().equals(minus)) {
      add_op();
      term();
      term_tail();
      printIndentExit(term_tail);
    }else if (token.getName().equals(id) || token.getName().equals(rpar) || token.getName().equals(read)
            || token.getName().equals(write) || token.getName().equals(endSymbol)) {
      printMatch("\u03B5");
    } else parseError();
  }

  private void term () {
    printIndentEntry(term);
    if(token.getName().equals(id) || token.getName().equals(number) || token.getName().equals(lpar) ) {
      factor();
      factor_tail();
      printIndentExit(term);
    }else parseError();
  }

  private void factor_tail () {
    printIndentEntry(factor_tail);
    if(token.getName().equals(times)) {
      mult_op();
      factor();
      factor_tail();
      printIndentExit(factor_tail);
    } else if (token.getName().equals(plus) || token.getName().equals(minus) || token.getName().equals(rpar)) {
      printMatch("\u03B5");
    } else parseError();
  }

  private void factor () {
    printIndentEntry(factor);
    if(token.getName().equals(id)){
      printScript(id);
      printIndentExit(factor);
    }else if (token.getName().equals(number)){
      printScript(number);
    }else if (token.getName().equals(lpar)){
      match(lpar);
      expr();
      match(rpar);
    }
  }

  private void add_op () {
  if (token.getName().equals(plus))
    match(plus);
  else if(token.getName().equals(minus))
    match(minus);
  else parseError();
  }

  private void mult_op () {
  if (token.getName().equals(times))
    match(times);
  else parseError();
  }
}

class terminalToken{

  private String value;
  private String name;

  terminalToken(){

  }

  terminalToken (String value){
    this.value = value;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
