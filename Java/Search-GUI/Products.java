import java.io.*;
import java.util.*;

public class Products {
    int numberOfProducts;
    String productName;
    int productCode; 
    String productDescirpt;
    String productPrice;    //? change to double?
    String productImage;
    int blank;
    String USERSEARCH = "";

    Products[] productsArr;
    Products[] searchedArr;

    public static Products[] staticArray;

    // constructor
    public Products() {
        numberOfProducts = 0;
        productName = "";
        productCode = 0;
        productDescirpt = "";
        productPrice = "";
        productImage = "";
        blank = 0;
    }
// debug through main by attempting to scan file!!
    public void scanProductFile() {
        try {
            File productsFile = new File("products.txt");
            Scanner scan = new Scanner(productsFile);

            int i = 0;
            numberOfProducts = scan.nextInt();
            productsArr = new Products[numberOfProducts];
            scan.nextLine();

            while (scan.hasNextLine()) {
                Products temp = new Products();
                temp.productName = scan.nextLine();
                temp.productCode = scan.nextInt();
                String ignoreThis = scan.nextLine();
                temp.productDescirpt = scan.nextLine();
                temp.productPrice = scan.nextLine();
                temp.productImage = scan.nextLine();

                productsArr[i] = temp;

                i++;
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: products.txt not found");
            System.exit(0);
        }
    }

    public Products[] getNewArray() {
        return searchedArr;
    }

    public Products[] getArray(String userIn) {
        String InStr = userIn.toUpperCase();
        USERSEARCH = InStr;
        System.out.println(InStr);
        scanProductFile();
        Products[] returnArr;
        Products[] tempArr = productsArr;
        int i = 0;
        int j = 0;
        int size = 0;

        while (j < numberOfProducts) {
            String tmep = tempArr[j].productName.toUpperCase();
            if (tmep.contains(InStr)) {
                size++;
            }
            j++;
        }
        size++;
        returnArr = new Products[size];
        j = 0;
        while (j < numberOfProducts) {
            String temp = tempArr[j].productName.toUpperCase();
            if (temp.contains(InStr)) {
                returnArr[i] = productsArr[j];
                i++;
            }
            j++;
        }
        returnArr[i] = new Products();
        searchedArr = returnArr;
        return returnArr;
    }

    // return the products array
    public Products[] returnArray() {
        return productsArr;
    }
}
