// contoller class GUI
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import java.lang.Integer;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;

public class Controller extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    JFrame loginWindow;
    JFrame shoppingWindow;
    JFrame resultWindow;
    JFrame registerWindow;
    JFrame cartWindow;

    Font titleFont = new Font("Courier", Font.BOLD, 20);
    Font logoutFont = new Font("Arial", Font.PLAIN, 10);
    Font subTitleFont = new Font("Arial", Font.BOLD, 14);
    
    //* login window global variables
    JTextField lastnameIn = new JTextField();
    JTextField rewardNumIn = new JTextField();
    JLabel errorMsg = new JLabel("Login not valid, try again or Register to join");

    //*register window global variables
    //TODO fix Rnum? or leave it
    int RNum = 4562340;
    String finalNum = Integer.toString(RNum);
    JTextField firstnameIn = new JTextField();
    JTextField lastnameInp = new JTextField();
    JTextField eliteSIn = new JTextField();
    JTextField emailIn = new JTextField();
    JTextField phoneNumIn = new JTextField();
    JLabel errMsg = new JLabel("PLEASE FILL OUT ALL BOXES TO REGISTER.");
    JButton closeRegisterWindow = new JButton("DONE");
    JLabel newRewardNum = new JLabel(finalNum);
    JLabel rewardnumLabel = new JLabel("Your new reward number is:");
    JButton returnToLoginButton = new JButton("Return to Login");

    //* shopping window globals
    JTextField searchField = new JTextField();
    Products[] RecievedArr;
    String HELO = "";

    //* result window globals
    JButton ZeroButt = new JButton("add to cart");
    JButton OneButt = new JButton("add to cart");
    JButton TwoButt = new JButton("add to cart");
    JButton ThreeButt = new JButton("add to cart");
    JButton FourButt = new JButton("add to cart");
    JButton FiveButt = new JButton("add to cart");
    JButton SixButt = new JButton("add to cart");
    JButton SevenButt = new JButton("add to cart");
    JButton EightButt = new JButton("add to cart");
    JButton NineButt = new JButton("add to cart");
    JButton TenButt = new JButton("add to cart");
    JButton ElevenButt = new JButton("add to cart");

    //* cart global variables
    Products[] productsInCart = new Products[60];
    int cartCount = 0;
    JLabel noResults = new JLabel("Nothing in Cart.");
    Carts cartResults = new Carts();
    
    //! LOGIN WINDOW
    public void loginController() {

        // instantiate current frame
        loginWindow = new JFrame("Login Window");
        loginWindow.setSize(WIDTH, HEIGHT);
        loginWindow.setResizable(false);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // variables (buttons and labels)
        JLabel loginTitle = new JLabel("LOGIN PAGE");
        JButton loginButton = new JButton("LOGIN");
        JButton registerButton = new JButton("REGISTER");
        JLabel loginLabel = new JLabel("Lastname:");
        JLabel rewardNLabel = new JLabel("Reward Number:");
        
        // bounds of variables
        loginTitle.setBounds(5,5,300,50);
        loginButton.setBounds(100, 350, 100, 30);
        registerButton.setBounds(300, 350, 100, 30);
        lastnameIn.setBounds(225, 200, 150, 30);
        rewardNumIn.setBounds(225, 250, 150, 30);
        loginLabel.setBounds(100, 200, 90, 30);
        rewardNLabel.setBounds(100, 250,100, 30);
        errorMsg.setBounds(50, 175, 250, 20);

        // variable color / size
        loginTitle.setFont(titleFont);
        loginButton.setBackground(Color.green);
        registerButton.setBackground(Color.red);
        errorMsg.setForeground(Color.red);

        // add variables to screen
        loginWindow.add(loginTitle);
        loginWindow.add(loginButton);
        loginWindow.add(registerButton);
        loginWindow.add(lastnameIn);
        loginWindow.add(rewardNumIn);
        loginWindow.add(loginLabel);
        loginWindow.add(rewardNLabel);
        loginWindow.add(errorMsg);

        // layout manager
        loginWindow.setLayout(null);

        // Window visibility
        loginWindow.setVisible(true);
        errorMsg.setVisible(false);

        // action listeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    //! SHOPPING WINDOW
    public void shoppingController() {
        // instantiate current frame
        shoppingWindow = new JFrame("Shopping Window");
        shoppingWindow.setSize(WIDTH, HEIGHT);
        shoppingWindow.setResizable(false);
        shoppingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add table here?

        // variables (Buttons and labels)
        JLabel pageTitle = new JLabel("SHOPPING PAGE");
        JButton searchButton = new JButton("SEARCH");
        JButton logoutButton = new JButton("Logout");
        JButton viewCartButton = new JButton("View Cart");
        JLabel searchLabel = new JLabel("SEARCH:");

        // bounds of variables
        pageTitle.setBounds(5,5,300,50);
        searchButton.setBounds(450,65,100,30);
        logoutButton.setBounds(470, 25, 70, 20);
        viewCartButton.setBounds(380, 25, 80, 20);
        searchLabel.setBounds(5, 65, 100, 30);
        searchField.setBounds(101, 65, 300, 30);

        // variable color / font
        pageTitle.setFont(titleFont);
        logoutButton.setFont(logoutFont);
        viewCartButton.setFont(logoutFont);

        // add vairables to screen
        shoppingWindow.add(pageTitle);
        shoppingWindow.add(searchButton);
        shoppingWindow.add(logoutButton);
        shoppingWindow.add(searchLabel);
        shoppingWindow.add(searchField);
        shoppingWindow.add(viewCartButton);

        // layout manager
        shoppingWindow.setLayout(null);

        // visibility
        shoppingWindow.setVisible(true);

        // action listeners
        logoutButton.addActionListener(this);
        searchButton.addActionListener(this);
        viewCartButton.addActionListener(this);
    }

    //! SEARCH RESULTS WINDOW
    public void resultController(Products[] resultArr) {
        Products[] results = resultArr;
        int numResults = resultArr.length;
        int count = 0;
        int YPAGE = 90;

        // instantiate frame
        resultWindow = new JFrame("Search Results");
        resultWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultWindow.setSize(WIDTH, HEIGHT);
        resultWindow.setResizable(false);
        resultWindow.setLayout(null);

        // variables
        JLabel noResults = new JLabel("No results, check spelling or try searching a new item");
        JLabel resultTitle = new JLabel("Results");
        JButton returnToSearch = new JButton("Return to Search");
        JButton logoutSR = new JButton("Logout");

        // bounds
        resultTitle.setBounds(5,5,300,50);
        returnToSearch.setBounds(330, 25, 130,20);
        logoutSR.setBounds(470, 25, 70,20);
        noResults.setBounds(20, 70, 400,30);


        // fonts n such
        resultTitle.setFont(titleFont);
        returnToSearch.setFont(logoutFont);
        logoutSR.setFont(logoutFont);
        noResults.setForeground(Color.RED);

        // add to window
        resultWindow.add(returnToSearch);
        resultWindow.add(resultTitle);
        resultWindow.add(logoutSR);

        if (results.length == 1) {
            resultWindow.add(noResults);
        } else {
            for (int i = 0; i < numResults-1; i++) {
                resultWindow.add(new JLabel(results[count].productName)).setBounds(20,YPAGE,200,20);
                String tempCode = Integer.toString(results[count].productCode);
                resultWindow.add(new JLabel(tempCode)).setBounds(230,YPAGE,50,20);
                resultWindow.add(new JLabel("$"+results[count].productPrice)).setBounds(300,YPAGE,90,20);
                if (i == 0) {
                    resultWindow.add(ZeroButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 1) {
                    resultWindow.add(OneButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 2) {
                    resultWindow.add(TwoButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 3) {
                    resultWindow.add(ThreeButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 4) {
                    resultWindow.add(FourButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 5) {
                    resultWindow.add(FiveButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 6) {
                    resultWindow.add(SixButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 7) {
                    resultWindow.add(SevenButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 8) {
                    resultWindow.add(EightButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 9) {
                    resultWindow.add(NineButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 10) {
                    resultWindow.add(TenButt).setBounds(355,YPAGE,110,25);
                }
                if (i == 11) {
                    resultWindow.add(ElevenButt).setBounds(355,YPAGE,110,25);
                }
        
                YPAGE += 40;
                count++;
            }
        }

        // visibility
        resultWindow.setVisible(true);

        // action listeners
        returnToSearch.addActionListener(this);
        logoutSR.addActionListener(this);
        ZeroButt.addActionListener(this);
        OneButt.addActionListener(this);
        TwoButt.addActionListener(this);
        ThreeButt.addActionListener(this);
        FourButt.addActionListener(this);
        FiveButt.addActionListener(this);
        SixButt.addActionListener(this);
        SevenButt.addActionListener(this);
        EightButt.addActionListener(this);
        NineButt.addActionListener(this);
        TenButt.addActionListener(this);
        ElevenButt.addActionListener(this);
    }

    //! REGISTER WINDOW
    public void registerController() {
        // instantiate frame
        registerWindow = new JFrame("Register Window");
        registerWindow.setSize(WIDTH, HEIGHT);
        registerWindow.setResizable(false);
        registerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // variables (local)
        JLabel registerTitle = new JLabel("Registration");
        JLabel firstname = new JLabel("Firstname:");
        JLabel lastname = new JLabel("Lastname:");
        JLabel eStatus = new JLabel("Would you like to be an\n elite member? (yes/no)");
        JLabel emailL = new JLabel("Email:");
        JLabel pNumL = new JLabel("Phone number:");

        // variable bounds
        registerTitle.setBounds(10, 10, 300, 50);
        firstname.setBounds(10, 100, 100, 40);
        lastname.setBounds(10, 150, 100, 40);
        eStatus.setBounds(10, 200, 280, 40);
        emailL.setBounds(10, 250, 100, 40);
        pNumL.setBounds(10, 300, 100, 40);
        closeRegisterWindow.setBounds(275, 400, 100, 30);
        returnToLoginButton.setBounds(275, 440, 150, 30);
        newRewardNum.setBounds(80, 440, 200, 30);
        firstnameIn.setBounds(110, 105, 270, 30);
        lastnameIn.setBounds(110, 155, 270, 30);
        eliteSIn.setBounds(290, 205, 90, 30);
        emailIn.setBounds(110, 255, 270, 30);
        phoneNumIn.setBounds(110, 305, 270, 30);
        errMsg.setBounds(90, 60, 300, 40);
        rewardnumLabel.setBounds(40, 400, 200, 30);

        // variable color / font
        registerTitle.setFont(titleFont);
        errMsg.setForeground(Color.red);

        // add variables to screen
        registerWindow.add(registerTitle);
        registerWindow.add(firstname);
        registerWindow.add(lastname);
        registerWindow.add(eStatus);
        registerWindow.add(emailL);
        registerWindow.add(pNumL);
        registerWindow.add(closeRegisterWindow);
        registerWindow.add(firstnameIn);
        registerWindow.add(lastnameIn);
        registerWindow.add(eliteSIn);
        registerWindow.add(emailIn);
        registerWindow.add(phoneNumIn);
        registerWindow.add(errMsg);
        registerWindow.add(newRewardNum);
        registerWindow.add(returnToLoginButton);
        registerWindow.add(rewardnumLabel);

        // layout manager
        registerWindow.setLayout(null);

        //visiblity
        registerWindow.setVisible(true);
        errMsg.setVisible(false);
        newRewardNum.setVisible(false);
        returnToLoginButton.setVisible(false);
        closeRegisterWindow.setVisible(true);
        rewardnumLabel.setVisible(false);

        // action listeners
        closeRegisterWindow.addActionListener(this);
        returnToLoginButton.addActionListener(this);

    }

    //! CART WINDOW
    public void cartController() {
        String ProductArrString[] = new String[100];
        try {
            Scanner key = new Scanner(new FileInputStream("userSearch.txt"));
            String notThis = key.nextLine();
            int i = 0;
            do {
                ProductArrString[i] = key.nextLine();
                i++;
            } while (key.hasNextLine());
        } catch (Exception x) {
            System.out.println("error accessing userSearch");
        }
        // instantiate current frame
        cartWindow = new JFrame("Cart Window");
        cartWindow.setSize(WIDTH, HEIGHT);
        cartWindow.setResizable(false);
        cartWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // variables
        JLabel cartTitle = new JLabel("CART");
        JButton returnToShopping = new JButton("Return to Search");
        JButton cLogoutButton = new JButton("Logout");
        JLabel subTitle = new JLabel("Your Cart:");

        // variable bounds
        cartTitle.setBounds(5,5,300,50);
        returnToShopping.setBounds(330,25,130,20);
        cLogoutButton.setBounds(470,25,70,20);
        subTitle.setBounds(5, 50, 300, 50);
        noResults.setBounds(20, 90, 400, 30);

        // variable color / font
        cartTitle.setFont(titleFont);
        returnToShopping.setFont(logoutFont);
        cLogoutButton.setFont(logoutFont);
        subTitle.setFont(subTitleFont);
        noResults.setForeground(Color.red);
        noResults.setFont(titleFont);

        // add to screen
        cartWindow.add(cartTitle);
        cartWindow.add(returnToShopping);
        cartWindow.add(cLogoutButton);
        cartWindow.add(subTitle);
        cartWindow.add(noResults);

        int YPAGET = 90;
        if (ProductArrString[0] == null) {
            noResults.setVisible(true);
        } else {
            noResults.setVisible(false);
            int j = 0;
            while (ProductArrString[j] != null) {
                String tempS = ProductArrString[j];
                cartWindow.add(new JLabel(tempS)).setBounds(20, YPAGET, 300, 20);
                YPAGET += 40;
                j++;
            }
        }



        // layout manager
        cartWindow.setLayout(null);

        // visibility
        cartWindow.setVisible(true);
        //* display user cart additions

        // action listeners
        returnToShopping.addActionListener(this);
        cLogoutButton.addActionListener(this);
    }

    public String searchInput;
    
    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
        Products rArray[];
        Products temp = new Products();

        //! BUTTONS IN LOGIN WINDOW
        // if login button is pushed
        if (buttonString.equals("LOGIN")) {
            String lastnameInput = lastnameIn.getText();
            String rewardNInput = rewardNumIn.getText();

            // TODO change this to call function to compare credentials LATER
            CustomerFileV2 tempC = new CustomerFileV2();
            if (tempC.compareLogin(lastnameInput, rewardNInput)) {
                loginWindow.dispose();
                Controller ShopWindow = new Controller();
                ShopWindow.shoppingController();
            } else {
                errorMsg.setVisible(true);
            }
        }

        // if register button is pushed
        if (buttonString.equals("REGISTER")) {
            loginWindow.dispose();
            Controller registerWindow = new Controller();
            registerWindow.registerController();
        }

        //! BUTTONS IN SHOPPING WINDOW
        //logout button
        //TODO save products they had to cart/products
        if (buttonString.equals("Logout")) {
            if (this.shoppingWindow != null) {
                shoppingWindow.dispose();
            } else if (this.cartWindow != null) {
                cartWindow.dispose();
            } else if (this.resultWindow != null) {
                resultWindow.dispose();
            }
            Controller newLoginWindow = new Controller();
            newLoginWindow.loginController();
        }

        // view cart button
        if (buttonString.equals("View Cart")) {
            shoppingWindow.dispose();
            Controller newCartWindow = new Controller();
            newCartWindow.cartController();
        }

        // search button
        if (buttonString.equals("SEARCH")) {
            this.searchInput = searchField.getText();

            //! append the search input to the userSearch.txt
            //todo search input into the userSearch.txt file
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt");
                tempFile.write(this.searchInput+"\n");
                tempFile.flush();
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }

            Products[] retrievedArr = temp.getArray(this.searchInput);
            Products.staticArray = retrievedArr;
            shoppingWindow.dispose();
            Controller resultWin = new Controller();
            resultWin.resultController(retrievedArr);
        }

        //! buttons in result window
        Carts toCartFile = new Carts();
        Scanner keyboard = null;
        if (e.getSource() == ZeroButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[0].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[0];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[0].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        }
        if (e.getSource() == OneButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[1].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[1];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[1].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        }
        if (e.getSource() == TwoButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[2].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[2];

            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[2].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        }
        if (e.getSource() == ThreeButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[3];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[3].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        }   

        if (e.getSource() == FourButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[4];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[4].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == FiveButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[5];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[5].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == SixButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[6];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[6].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == SevenButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[7];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[7].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == EightButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[8];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[8].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == NineButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[9];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[9].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == TenButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[10];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[10].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 
        if (e.getSource() == ElevenButt) {
            try {
                keyboard = new Scanner(new FileInputStream("userSearch.txt"));
                HELO = keyboard.nextLine();
                RecievedArr = temp.getArray(HELO);
                //toCartFile.addToCart(RecievedArr[3].productCode);
            } catch (FileNotFoundException e1) {
                System.out.println("userSearch File not found");
            }
            productsInCart[cartCount] = RecievedArr[11];
            try{
                FileWriter tempFile = new FileWriter("userSearch.txt",true);
                tempFile.append(this.RecievedArr[11].productName+"\n");
                tempFile.close();

            }
            catch(IOException z){
                z.printStackTrace();
            }
            cartCount++;
        } 

        //! BUTTONS IN REGISTER WINDOW
        // if registerWindow "done" button is pushed
        if (buttonString.equals("DONE")) {
            CustomerV2 newCustomer = new CustomerV2();

            if (firstnameIn.getText().isEmpty() || lastnameIn.getText().isEmpty() || eliteSIn.getText().isEmpty() || emailIn.getText().isEmpty() || phoneNumIn.getText().isEmpty()) {
                errMsg.setVisible(true);
            } else {
                newCustomer.firstName = firstnameIn.getText();
                newCustomer.lastName = lastnameIn.getText();
                newCustomer.status= eliteSIn.getText();
                //* newCustomer.rewardNumber = newCustomer.addCustomerNum();
                // regular or elite (discount default set to 10%)
                if (newCustomer.status.equalsIgnoreCase("yes")) {
                    newCustomer.discountOrStarsEarned = .1;
                } else {
                    newCustomer.discountOrStarsEarned = 0;
                }
                newCustomer.email = emailIn.getText();
                newCustomer.mobile = phoneNumIn.getText();

                // set new number in label and make visible to user
                newCustomer.rewardNum = String.valueOf(RNum);
                newRewardNum.setVisible(true);
                rewardnumLabel.setVisible(true);
                returnToLoginButton.setVisible(true);
                closeRegisterWindow.setVisible(false);
                RNum++;

                // add customer to customer array
                newCustomer.placeInArray(newCustomer);
            }
        }

        // if return to loging button is pushed
        if (buttonString.equals("Return to Login")) {
            registerWindow.dispose();
            Controller toLogin = new Controller();
            toLogin.loginController();
        } 

        //! BUTTONS IN CART WINDOW
        // if return to search button is pushed
        if (buttonString.equals("Return to Search")) {
            if (this.cartWindow != null) {
                cartWindow.dispose();
            } 
            if (this.resultWindow != null) {
                resultWindow.dispose();
            }
            Controller toShopping = new Controller();
            toShopping.shoppingController();
        }        
    }    
}
