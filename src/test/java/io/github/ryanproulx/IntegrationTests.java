package io.github.ryanproulx;

import java.math.BigDecimal;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegrationTests {

  private Store store;

  @BeforeEach
  public void setup() {
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
    store = new Store(warehouse, ui, cart);
  }

  /**
   * MacBook Pro promotion: Each sale of a MacBook Pro comes with a free Raspberry Pi B
   */
  @Test
  void macbookProPromotion() {
    store.addToCart("43N23P");
    String result = store.checkOut();
    Assertions.assertEquals("Scanned Items: Raspberry Pi B, MacBook Pro\n"
        + "Total: $5399.99", result);
  }

  /**
   * MacBook Pro promotion: Each sale of a MacBook Pro comes with a free Raspberry Pi B
   * Check to see if the system doesn't give the user more Pi's than is actually in stock
   */
  @Test
  void moreMacbookProsThanRaspberryPisInStock() {
    store.addToCart("43N23P");
    store.addToCart("43N23P");
    store.addToCart("43N23P");
    String result = store.checkOut();
    Assertions.assertEquals("Scanned Items: Raspberry Pi B, Raspberry Pi B, MacBook Pro, "
        + "MacBook Pro, MacBook Pro\n"
        + "Total: $16199.97", result);
  }

  /**
   * Buy 3 Google Homes for the price of 2
   */
  @Test
  void googleHomePromotion() {
    store.addToCart("120P90");
    store.addToCart("120P90");
    store.addToCart("120P90");
    String result = store.checkOut();
    Assertions.assertEquals("Scanned Items: Google Home, Google Home, Google Home\n"
        + "Total: $99.98", result);
  }

  /**
   * Alexa Speaker promotion: Buying more than 3 Alexa Speakers will have a 10% discount on all
   * Alexa speakers
   */
  @Test
  void alexaSpeakerPromotion() {
    store.addToCart("A304SD");
    store.addToCart("A304SD");
    store.addToCart("A304SD");
    store.addToCart("A304SD");
    String result = store.checkOut();
    Assertions.assertEquals("Scanned Items: Alexa Speaker, Alexa Speaker, "
        + "Alexa Speaker, Alexa Speaker\n"
        + "Total: $394.20", result);
  }

  @Test
  void addMoreToCartThanInStock() {
    store.addToCart("234234");
    store.addToCart("234234");
    Assertions.assertFalse(store.addToCart("234234"));
    String result = store.checkOut();
    Assertions.assertEquals("Scanned Items: Raspberry Pi B, Raspberry Pi B\n"
        + "Total: $60.00", result);
  }

}
