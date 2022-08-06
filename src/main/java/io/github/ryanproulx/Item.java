package io.github.ryanproulx;

import java.math.BigDecimal;

public class Item {

  private final Product product;
  private final BigDecimal unitPrice;
  private int quantity;

  public Item(Product product, int qty, BigDecimal unitPrice) {
    this.quantity = qty;
    this.unitPrice = unitPrice;
    this.product = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public BigDecimal getUnitPrice() {
    return this.unitPrice;
  }

  public BigDecimal getPrice() {
    return this.unitPrice.multiply(new BigDecimal(this.quantity));
  }

  public void increaseQuantity() {
    this.quantity++;
  }

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
