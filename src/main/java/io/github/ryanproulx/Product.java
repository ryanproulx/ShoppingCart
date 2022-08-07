package io.github.ryanproulx;

/**
 *
 */
public class Product {

  private final String sku;
  private final String name;

  /**
   *
   * @param sku Product SKU identifier.
   * @param name Product name
   */
  public Product(String sku, String name) {
    this.sku = sku;
    this.name = name;
  }

  /**
   *
   * @return Product SKU
   */
  public String getSku() {
    return sku;
  }

  /**
   *
   * @return Product name
   */
  public String getName() {
    return name;
  }

}
