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

    public void listAllProducts() {
        if (this.products.size() > 0) {
            System.out.println("Products:");
            for (Product product : this.products.keySet()) {
                System.out.println("    " + product.toString() + "; Quantity: " + this.products.get(product));
            }
        } else {
            System.out.println("No products found in " + this.id + " branch");
        }
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

    public void addProduct(Product product) throws Exception {
        if (this.isAuthenticated) {
            if (this.products.containsKey(product)) {
                this.products.put(product, this.products.get(product) + 1);
            } else {
                this.products.put(product, 1);
            }
        } else {
            throw new Exception("You are not authenticated");
        }
    }

    public void removeProduct(Product product, Integer quantity) throws Exception {
        if (this.isAuthenticated) {
            if (this.products.containsKey(product)) {
                if (this.products.get(product) > quantity) {
                    this.products.put(product, this.products.get(product) - quantity);
                } else {
                    this.products.remove(product);
                } 
            } else {
                throw new Exception("Product not found in " + this.id + " branch");
            }  
        } else {
            throw new Exception("You are not authenticated");
        }        
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

    public void setAddress(Address address) throws Exception {
        if (this.isAuthenticated) {
            this.address = address;
        } else {
            throw new Exception("You are not authenticated");
        }
    }

    public Boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public String toString() {
        return "Branch: " + this.id + "\n" + "Address: " + this.address.toString();
    }
}
