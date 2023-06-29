package models;

public abstract class Product {
    protected String name;
    protected Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product: %s; Price: %.2f", this.name, this.price);
    }
}