package io.github.ryanproulx;

import java.util.Scanner;

/**
 *
 */
public class UserInterface {

  private final Scanner scanner;

  public UserInterface(Scanner scanner) {
    this.scanner = scanner;
  }

  public void start(Store store) {

    System.out.println("Welcome to the store!\n");
    System.out.println("Below is the complete inventory:");

    for (Product product : store.getWarehouseInventory()) {
      System.out.println(product.getName() + " (" + product.getSku() + ")");
    }

    System.out.println(
        "\nTo add a product to your shopping cart, please enter the product's SKU. Press return "
            + "once you are ready to checkout. ");

    while (true) {
      System.out.print("Enter SKU: ");
      String sku = scanner.nextLine();
      if (sku.isEmpty()) {
        break;
      }

      boolean result = store.addToCart(sku);

      if (!result) {
        System.out.println("Could not add to cart!");
      }
    }

    System.out.println(store.checkOut());


  }

}
