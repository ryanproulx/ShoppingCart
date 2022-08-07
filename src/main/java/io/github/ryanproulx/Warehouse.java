package io.github.ryanproulx;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The warehouse stores a product inventory, with product unit prices.
 */
public class Warehouse {

  private final Map<String, BigDecimal> prices;
  private final Map<String, Product> products;
  private final Map<String, Integer> stock;

  public Warehouse() {
    this.prices = new HashMap<>();
    this.products = new HashMap<>();
    this.stock = new HashMap<>();
  }

  /**
   *
   * @param product Product to be added to the warehouse inventory.
   * @param price Product unit price.
   * @param stock Amount of product to be added to the inventory.
   */
  public void addProduct(Product product, BigDecimal price, int stock) {
    this.products.put(product.getSku(), product);
    this.prices.put(product.getSku(), price);
    this.stock.put(product.getSku(), stock);
  }

  /**
   *
   * @param sku Product SKU identifier.
   * @return Product unit price.
   */
  public BigDecimal getPrice(String sku) {
    return this.prices.getOrDefault(sku, new BigDecimal("0.00"));
  }

  /**
   *
   * @param product Product to check for unit price
   * @return Product unit price.
   */
  public BigDecimal getPrice(Product product) {
    String sku = product.getSku();
    return this.getPrice(sku);
  }

  /**
   *
   * @param product Product to check inventory quantity
   * @return Quantity of product stocked in the warehouse inventory.
   */
  public int getStock(String product) {
    return this.stock.getOrDefault(product, 0);
  }

  /**
   *
   * @param sku SKU identifier of the product to be taken from the warehouse.
   * @return Product taken.
   */
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

  /**
   *
   * @return Get complete list of products.
   */
  public Collection<Product> getProducts() {
    return this.products.values();
  }

}
