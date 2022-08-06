package io.github.ryanproulx;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    // Set up the initial inventory based on the challenge description
    Product googleHome = new Product("120P90", "Google Home");
    Product macbookPro = new Product("43N23P", "MacBook Pro");
    Product alexaSpeaker = new Product("A304SD", "Alexa Speaker");
    Product raspberryPiB = new Product("234234", "Raspberry Pi B");

    // Create a new warehouse, add all the products for storage
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct(googleHome, new BigDecimal("49.99"), 10);
    warehouse.addProduct(macbookPro, new BigDecimal("5399.99"), 5);
    warehouse.addProduct(alexaSpeaker, new BigDecimal("109.50"), 10);
    warehouse.addProduct(raspberryPiB, new BigDecimal("30.00"), 2);

    Scanner scanner = new Scanner(System.in);
    UserInterface ui = new UserInterface(scanner);

    ShoppingCart cart = new ShoppingCart();
    Store store = new Store(warehouse, ui, cart);

    store.shop();
  }
}