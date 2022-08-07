package io.github.ryanproulx;

import java.math.BigDecimal;

/**
 * Item represents a product's unit price, and quantity. Used by the shopping cart.
 */
public class Item {

  private final Product product;
  private final BigDecimal unitPrice;
  private int quantity;

  /**
   *
   * @param product Product to be purchased by the user.
   * @param qty Quantity of products added as an item to the shopping cart.
   * @param unitPrice Individual unit price of a product as listed in the Warehouse.
   */
  public Item(Product product, int qty, BigDecimal unitPrice) {
    this.quantity = qty;
    this.unitPrice = unitPrice;
    this.product = product;
  }

  /**
   *
   * @return Get quantity of items.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   *
   * @return Get unit price.
   */
  public BigDecimal getUnitPrice() {
    return this.unitPrice;
  }

  /**
   *
   * @return Get total price of every product in this item.
   */
  public BigDecimal getPrice() {
    return this.unitPrice.multiply(new BigDecimal(this.quantity));
  }

  /**
   * Increase the quantity of this item.
   */
  public void increaseQuantity() {
    this.quantity++;
  }

  /**
   *
   * @return String representation of an item.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.quantity; i++) {
      sb.append(this.product.getName());

      if (i == this.quantity - 1) {
        break;
      }

      sb.append(", ");
    }
    return sb.toString();
  }

}
