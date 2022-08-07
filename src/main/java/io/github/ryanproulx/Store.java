package io.github.ryanproulx;

import java.util.Collection;

/**
 * The store is responsible for handling any requests from the user interface to add products
 * to their shopping cart, and to complete a checkout process.
 *
 * It has access to the Warehouse to determine inventory stock and prices.
 */
public class Store {

  private final Warehouse warehouse;

  private final UserInterface ui;
  private final ShoppingCart cart;

  /**
   *
   * @param warehouse Warehouse contains an inventory and prices of all products in the system.
   * @param ui User interface used to add items to the shopping cart and complete purchases.
   * @param cart Shopping cart used to store items.
   */
  public Store(Warehouse warehouse, UserInterface ui, ShoppingCart cart) {
    this.warehouse = warehouse;
    this.ui = ui;
    this.cart = cart;
  }

  public void shop() {

    this.ui.start(this);
  }

  /**
   * Adds products as items to a user's shopping cart. If a product does not exist, or there
   * are not enough left in the inventory, the item will not be added to the cart.
   *
   * This method currently has one promotion tied to it.
   * @param sku SKU identifier of a product to be added to the cart by a user.
   * @return Boolean return if a product can be added to the cart.
   */
  public boolean addToCart(String sku) {
    if (this.warehouse.getStock(sku) > 0) {
      Product product = this.warehouse.take(sku);

      // Promotion: Each sale of a MacBook Pro comes with a free Raspberry Pi B
      if (sku.equals("43N23P")) {
        Product freeItem = this.warehouse.take("234234");
        if (freeItem != null) {
          cart.add(freeItem, warehouse.getPrice(freeItem));
        }
      }
      cart.add(product, warehouse.getPrice(sku));
      return true;
    }
    return false;
  }

  /**
   *
   * @return All products currently listed in the warehouse.
   */
  public Collection<Product> getWarehouseInventory() {
    return this.warehouse.getProducts();
  }

  public String checkOut() {
    return this.cart.toString();
  }
}
