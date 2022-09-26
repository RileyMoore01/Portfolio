- **Overview:** <br />
  This program reads in 3 text files {customers, products, and cart} and stores this information in the corresponding object.
  Once the arrays(using ArrayList) are defined the user may be able to search for the product under their customer account on the GUI.
  A new customer may create an account upon login, recieving a reward number to login with as well as last name.
  Once logged in, the customer can add, remove, and view their products in the cart. 
  Finally the customer will be able to checkout and recieve a receipt in their email (read in from customer text).
  Other Functions: Add customers, remove customers, change customer information, privacy leak and eception handling.


- **Objective:** <br />
Develop an ecommerce system based on the given scenario.
Scenario: You are a Software Engineer Level I and are charged with the task of developing an object-oriented system to allow
customers to register, to log on, to log off, to search for products, and to put products into a shopping cart. This system must
maintain:
- a file of customers, “customers.txt”
- a file of products, “products.txt”
- a file of carts and contents, “carts.txt”


The file “customers.txt” has the following information on each line for each customer. You may presume that this file is correctly
formatted and has the stated number of customers. This file would be input when the program is first executed and output when
execution ends if changes are made. The file should be in ascending order of reward number. Customers may be elite reward or
regular reward customers. Elite reward customers have a discount percentage and regular reward customers have the number of
stars earned.
- Total Number of customers
- First Name
- Last Name
- Reward Number and Elite Status
- Percentage discount or stars earned
- Email Address
- Mobile Phone Number
- …

The file “products.txt” is provided with the following information on each line for each product. You may presume that this file is
correctly formatted and has the stated number of customers. This file is input when the program is first executed and output when
execution ends. This file is kept in ascending product number order.
- Total Number of products
- Product Name
- Product Number
- Description
- Price
- Image File Name or NA if no image file
- …


An example “carts.txt” file would be given as below where each reward number is followed by the number of cart items and the
products in the cart. Not every customer may have a cart of items.
- 3
- 00256789
- 2
- 6450834 1
- 6464572 2
- 00256790
- 1
- 6450834 1
- 00256792
- 3
- 6134341 3
- 6464572 1
- 6450834 1
