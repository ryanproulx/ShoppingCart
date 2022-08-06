package io.github.ryanproulx;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {

  private final Map<String, BigDecimal> prices;
  private final Map<String, Product> products;
  private final Map<String, Integer> stock;

  public Warehouse() {
    this.prices = new HashMap<>();
    this.products = new HashMap<>();
    this.stock = new HashMap<>();
  }

  public void addProduct(Product product, BigDecimal price, int stock) {
    this.products.put(product.getSku(), product);
    this.prices.put(product.getSku(), price);
    this.stock.put(product.getSku(), stock);
  }

  public BigDecimal getPrice(String product) {
    return this.prices.getOrDefault(product, new BigDecimal("0.00"));

  }

  public int getStock(String product) {
    return this.stock.getOrDefault(product, 0);
  }

  public Product take(String sku) {
    if (!this.stock.containsKey(sku)) {
      return null;
    }

    int stockQty = this.stock.get(sku);

    if (stockQty <= 0) {
      return null;
    }

    this.stock.put(sku, stockQty - 1);
    return this.products.get(sku);
  }

  public Collection<Product> getProducts() {
    return this.products.values();
  }

}
