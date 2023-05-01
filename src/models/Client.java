package models;

import java.util.HashMap;

public class Client {
    private String name;
    private String cpf;
    private Integer age;
    private HashMap<Product, Integer> purchasedProducts = new HashMap<>();

    public Client(String name, String cpf, Integer age) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
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

    public void buyProduct(Product product) {
        if (this.purchasedProducts.containsKey(product)) {
            this.purchasedProducts.put(product, this.purchasedProducts.get(product) + 1);
        } else {
            this.purchasedProducts.put(product, 1);
        }
        // remove from stock
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

    public String getCPF() {
        return this.cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "CPF: " + this.cpf + "\n" + "Age: " + this.age + "\n";
    }
}
