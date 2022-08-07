# ShoppingCart
ShoppingCart is a small standalone CLI-based Java application. It implements checkout business logic that supports different promotions with the given inventory.

Concepts implemented:
- **Product**: Represents products to be purchased. Includes a product name and SKU identifier.
- **Warehouse**: Stores a product inventory, with product unit prices.
- **Store**: Responsible for handling any requests from the user interface to add products to their shopping cart, and to complete a checkout process. It has access to the Warehouse to determine inventory stock and prices.
- **Shopping Cart**: Used to store items that are added by the user. The shopping cart is responsible for adding products and calculating a final price.
- **Item**: Represents a product's unit price and quantity. Used by the shopping cart.

# Build instructions
Pre-requisites: [Java JDK 17 Eclipse Temurin](https://adoptium.net/temurin/releases/)

ShoppingCart includes [Maven Wrapper](https://maven.apache.org/wrapper/). You can build the project in a few different ways:

- Method 1 (Windows):
```
mvnw install
```

- Method 2 (Linux):
```
./mvnw install
```

- Method 3 (Windows or Linux):

> 1. Open the project directory in your Java IDE of choice as a Maven project. 
> 2. You can either use Maven to build and run the application, or Run the Main class using the IDE.


Maven will install the necessary prequisites, and build the project in the target directory. From there, a JAR file artifact will be located in the target directory.

# Usage
Once the project is built, you can use the following command to execute the Java application:
```
java -cp target/ShoppingCart-1.0-SNAPSHOT.jar io.github.ryanproulx.Main
```

From here, the user is presented with a CLI that shows the warehouse inventory of the store. Users can add products to their Shopping Cart item list by typing in the Product SKU that they wish to add to their cart. 

Once the user has added all of the items that they wish to add, simply pressing return (passing an empty string) will perform a Shopping Cart checkout and display the final receipt to the user.

# Future Work

- Data validation for products and items
- Separate promotion classes that implement promotions, separate out a promotion calculator
- More unit tests to test further edge cases
