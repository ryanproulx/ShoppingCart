package io.github.ryanproulx;

public class Product {

  private final String sku;
  private final String name;

  public Product(String sku, String name) {
    this.sku = sku;
    this.name = name;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

}
