//This program determines if a dna sample is a protien or not
public class DNA {
 
  public static void main(String[] args) {
 
    //  -. .-.   .-. .-.   .
    //    \   \ /   \   \ / 
    //   / \   \   / \   \  
    //  ~   `-~ `-`   `-~ `-
 
  //Instance Field for DNA
  String dna1 = "ATGCGATACGCTTGA";
  String dna2 = "ATGCGATACGTGA";
  String dna3 = "ATTAATATGTACTGA";
  String dna = dna1;

  //Finding the length of the DNA
  System.out.println(dna.length());

  //Start according to ATG
  int start = dna.indexOf("ATG");
  System.out.println("Start: " + start);

  //Stop according to TGA
  int end = dna.indexOf("TGA");
  System.out.println("End: " + end);

  //Determine if it is a protien
  if (start != -1 &&
    end != -1 &&
    (end - start) % 3 == 0) {
 
  String protein = dna.substring(start, end+3); 
  System.out.println("Protein: " + protein);
    } else {
      System.out.println("No protein found.");
    }
  }
}
