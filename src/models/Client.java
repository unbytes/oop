package models;

import java.util.HashMap;

public class Client {
    private String name;
    private String id;
    private Integer age;
    private HashMap<Product, Integer> purchasedProducts = new HashMap<>();
    private HashMap<Product, Integer> productsQueue = new HashMap<>();

    public Client(String name, String id, Integer age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public HashMap<Product, Integer> getProductsQueue() {
        return this.productsQueue;
    }

    public void addProductToQueue(Product product) {
        if (this.productsQueue.containsKey(product)) {
            this.productsQueue.put(product, this.productsQueue.get(product) + 1);
        } else {
            this.productsQueue.put(product, 1);
        }
    }

    public void removeProductFromQueue(Product product) {
        if (this.productsQueue.containsKey(product)) {
            if (this.productsQueue.get(product) > 1) {
                this.productsQueue.put(product, this.productsQueue.get(product) - 1);
            } else {
                this.productsQueue.remove(product);
            }
        }
    }

    public HashMap<Product, Integer> getPurchasedProducts() {
        return this.purchasedProducts;
    }

    public void addProductToPurchasedProducts(Product product) {
        if (this.purchasedProducts.containsKey(product)) {
            this.purchasedProducts.put(product, this.purchasedProducts.get(product) + 1);
        } else {
            this.purchasedProducts.put(product, 1);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "ID: " + this.id + "\n" + "Age: " + this.age + "\n";
    }
}
