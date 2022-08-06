package io.github.ryanproulx;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ShoppingCart {

  private final Map<String, Item> items;

  public ShoppingCart() {
    this.items = new HashMap<>();
  }

  public void add(Product product, BigDecimal price) {
    String sku = product.getSku();
    if (this.items.containsKey(sku)) {
      this.items.get(sku).increaseQuantity();
    } else {
      Item item = new Item(product, 1, price);
      this.items.put(sku, item);
    }
  }

  public BigDecimal calculatePrice() {
    BigDecimal price = new BigDecimal("0.00");
    BigDecimal totalDiscount = new BigDecimal("0.00");

    // MacBook Pro promotion: Each sale of a MacBook Pro comes with a free Raspberry Pi B
    Item raspberryPiB = this.items.get("234234");
    Item macBookPro = this.items.get("43N23P");
    if (raspberryPiB != null && macBookPro != null) {
      BigDecimal piDiscount = new BigDecimal(Math.min(macBookPro.getQuantity(),
          raspberryPiB.getQuantity())).multiply(raspberryPiB.getUnitPrice());
      totalDiscount = totalDiscount.add(piDiscount);
    }

    // Alexa Speaker promotion: Buying more than 3 Alexa Speakers will have a 10% discount on
    // all Alexa speakers
    Item alexaSpeaker = this.items.get("A304SD");
    if (alexaSpeaker != null && alexaSpeaker.getQuantity() > 2) {
      BigDecimal oldPrice = alexaSpeaker.getUnitPrice();
      BigDecimal alexaDiscount = oldPrice.multiply(new BigDecimal("10.00")).
          divide(new BigDecimal("100.00"));
      totalDiscount = totalDiscount.add(alexaDiscount);
    }

    // Google Home Promotion: Buy 3 Google Homes for the price of 2
    Item googleHome = this.items.get("120P90");
    if (googleHome != null) {
      int totalGoogleHomes = googleHome.getQuantity();
      int totalFreeGoogleHomes =
          totalGoogleHomes - ((totalGoogleHomes / 3) * 2 + totalGoogleHomes % 3);
      BigDecimal googleHomeDiscount =
          googleHome.getUnitPrice().multiply(BigDecimal.valueOf(totalFreeGoogleHomes));
      totalDiscount = totalDiscount.add(googleHomeDiscount);
    }

    for (Entry<String, Item> entry : this.items.entrySet()) {
      price = price.add(entry.getValue().getPrice());
    }
    return price.subtract(totalDiscount);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Scanned Items: ");

    for (Entry<String, Item> entry : this.items.entrySet()) {
      sb.append(entry.getValue());
      sb.append(", ");
    }
    sb.setLength(Math.max(sb.length() - 2, 0));
    sb.append("\nTotal: $");
    sb.append(this.calculatePrice());

    return sb.toString();
  }

}
