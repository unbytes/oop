package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Branch {
    private String id;
    private String password;
    private Address address;
    private Boolean isAuthenticated = false;
    private HashMap<Product, Integer> products = new HashMap<Product, Integer>();

    public Branch(String password, Address address) {
        this.id = UUID.randomUUID().toString().split("-")[0];
        this.password = password;
        this.address = address;
    }

    public HashMap<Product, Integer> getProducts() {
        return this.products;
    }

    public ArrayList<Product> searchProductsByWord(String word) {
        String manipulatedWord = word.toLowerCase().strip();
        ArrayList<Product> products = new ArrayList<Product>();

        for (Product product : this.products.keySet()) {
            if (product.getName().toLowerCase().contains(manipulatedWord)) {
                products.add(product);
            }
        }

        if (products.size() > 0) {
            products.sort((product1, product2) -> product1.getName().toLowerCase().compareTo(product2.getName().toLowerCase()));
            return products;
        } else {
            return null;
        }
    }

    public Product searchProductByName(String name) { 
        String manipulatedName = name.toLowerCase().strip();

        for (Product product : this.products.keySet()) {
            if (product.getName().toLowerCase().equals(manipulatedName)) {
                return product;
            }
        }

        return null;
    }

    public void addProduct(Product product, Integer productsQuantity) {
        this.products.put(product, productsQuantity);
    }

    public boolean removeProduct(Product product) {
        if (this.isAuthenticated) {
            if (this.products.containsKey(product)) {
                this.products.remove(product);
                return true;
            }
        }
        return false;
    }

    public boolean removeProduct(Product product, Integer quantity) {
        if (this.isAuthenticated) {
            if (this.products.containsKey(product)) {
                if (this.products.get(product) > quantity) {
                    this.products.put(product, this.products.get(product) - quantity);
                } else {
                    this.products.remove(product);
                }
                return true;
            }
        }
        return false;
    }

    public void buyProduct(Client client, Product product) {
        removeProduct(product, 1);
        client.addProductToPurchasedProducts(product);
    }

    public Boolean login(String password) {
        if (this.password.equals(password)) {
            this.isAuthenticated = true;
        }

        return this.isAuthenticated;
    }

    public void logout() {
        this.isAuthenticated = false;
    }

    public String getId() {
        return this.id;
    }

    public Address getAddress() {
        return this.address;
    }

    public boolean setAddress(Address address) {
        if (this.isAuthenticated) {
            this.address = address;
            return true;
        }
        return false;
    }

    public Boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public String toString() {
        return "Branch: " + this.id + "; Address: " + this.address.toString();
    }
}
