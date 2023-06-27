package models;

public abstract class Product {
    protected String name;
    protected Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product: %s; Price: %.2f", this.name, this.price);
    }
}