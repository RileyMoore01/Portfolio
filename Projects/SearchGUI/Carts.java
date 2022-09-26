import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public class Carts {

    //Cart Element Field
    int numCarts;
    String rewardNumber;
    int cartItems;
    ArrayList<Integer> products;
    ArrayList<Integer> items;

    //Array for cart information
    Carts[] cart;


    //--------Constructors-----------

    public Carts() {
        rewardNumber = "";
        cartItems = 0;
        products = new ArrayList<>();
        items = new ArrayList<>();
    }


    //--------------Scanner-----------------

    public void scanFile() {
        try {
            File cartsFile = new File("carts.txt");
            Scanner scan = new Scanner(cartsFile);

            int index = 0;                  //Tracks index of the Customer
            numCarts = scan.nextInt();      //Stroing the inital number of carts
            cart = new Carts[numCarts + 1];

            while(index < numCarts) {
                scan.nextLine();
                Carts temp = new Carts();
                temp.rewardNumber = scan.nextLine();
                temp.cartItems = scan.nextInt();
                for(int i = 0; i < temp.cartItems; i++) {
                    int product = scan.nextInt();
                    temp.products.add(product);
                    int item = scan.nextInt();
                    if(scan.hasNextLine()){
                        temp.items.add(item);
                    } else {
                        temp.items.add(0);
                    }
                } 
                cart[index] = temp;
                index ++;
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("***Error occured***");
            System.exit(0);
        }
    }


    //------------toString-----------------

    public String toString() {
        return "RewardNumber: " + this.rewardNumber + 
                "Cart Items: " + this.cartItems + 
                "Product Number: " + this.products + 
                "Total: "  + this.items;
    }

    //----------addToCart-----------------

    public void addToCart(int product) {    
        int index = 0;
        if(this.products.contains(product)) {
            index = this.products.indexOf(product);
            this.items.add(index, this.items.get(index) + 1);
        } else {
            this.products.add(product);
            int value = this.items.get(this.products.indexOf(product));
            this.items.add(value + 1);
        }    
    }
        
    
    //----------removeItem-----------------
    //Returns result Carts array with removed product
    
    public void removeItem(int product) {
        
        if (this.products.contains(product)) {
            int index = this.products.indexOf(product);
            int value = this.items.get(index);
            value -= 1;
            if (value <= 0) {       //If there are zero of the product in cart
                this.products.remove(product);  //remove product from list
                this.items.add(index, value);   //add new value of removed items
                this.items.remove(value);       //remove the new value in list
            } else {
                this.items.add(index, value);   //Otherwise add the new number of products to item list
            }
        }
    }
}
