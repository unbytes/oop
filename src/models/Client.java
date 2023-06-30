package models;

import java.util.HashMap;

/**
 * Representa um cliente nos dados da rede de farmácia
 * 
 * @autor Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class Client {
    private String name;
    private String cpf;
    private Integer age;
    private HashMap<Product, Integer> purchasedProducts = new HashMap<>();

    /**
     * Cria um objeto do tipo <code>Client</code>
     * 
     * @param name
     * @param cpf
     * @param age
     */
    public Client(String name, String cpf, Integer age) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }

    public HashMap<Product, Integer> getPurchasedProducts() {
        return this.purchasedProducts;
    }

    /**
     * Adiciona um produto nos produtos comprados pelo
     * cliente em uma unidade
     * 
     * @param product
     */
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
