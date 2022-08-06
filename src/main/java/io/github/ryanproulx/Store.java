package io.github.ryanproulx;

import java.util.Collection;

public class Store {

  private final Warehouse warehouse;

  private final UserInterface ui;
  private final ShoppingCart cart;

  public Store(Warehouse warehouse, UserInterface ui, ShoppingCart cart) {
    this.warehouse = warehouse;
    this.ui = ui;
    this.cart = cart;
  }

  // the method that handles the customers visit to the store.
  public void shop() {

    this.ui.start(this);
  }

  public boolean addToCart(String sku) {
    if (this.warehouse.getStock(sku) > 0) {
      Product product = this.warehouse.take(sku);

      // Promotion: Each sale of a MacBook Pro comes with a free Raspberry Pi B
      if (sku.equals("43N23P")) {
        Product freeItem = this.warehouse.take("234234");
        if (freeItem != null) {
          cart.add(freeItem, warehouse.getPrice("234234"));
        }
      }
      cart.add(product, warehouse.getPrice(sku));
      return true;
    }
    return false;
  }

  public Collection<Product> getWarehouseInventory() {
    return this.warehouse.getProducts();
  }

  public String checkOut() {
    return this.cart.toString();
  }
}
