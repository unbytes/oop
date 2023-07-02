package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Representa uma filial.
 * Contem informações sobre id, senha, endereço e produtos.
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class Branch {
    private String id;
    private String password;
    private Address address;
    private Boolean isAuthenticated = false;
    private HashMap<Product, Integer> products = new HashMap<Product, Integer>();

    /**
     * Cria um objeto do tipo Branch
     *
     * @param password Senha de acesso recebida pelo gerente
     * @param address  Endereço da filial
     */
    public Branch(String password, Address address) {
        this.id = UUID.randomUUID().toString().split("-")[0];
        this.password = password;
        this.address = address;
    }

    /**
     * Procura produtos por uma expressão existente em seu nome.
     * Caso nao sejam encontrados produtos com a expressão, retorna
     * <code>null</code>.
     *
     * @param word A expressão a ser procurada
     * @return ArrayList <code>products</code>
     */
    public ArrayList<Product> searchProductsByWord(String word) {
        String manipulatedWord = word.toLowerCase().strip();
        ArrayList<Product> products = new ArrayList<Product>();

        for (Product product : this.products.keySet()) {
            if (product.getName().toLowerCase().contains(manipulatedWord)) {
                products.add(product);
            }
        }

        if (products.size() > 0) {
            products.sort((product1, product2) -> product1.getName().toLowerCase()
                    .compareTo(product2.getName().toLowerCase()));
            return products;
        } else {
            return null;
        }
    }

    /**
     * Procura um produto por nome.
     * <p>
     * Retorna um objeto do tipo <code>Product</code> caso exista um objeto com o
     * nome procurado.
     * Caso nao seja encontrado, retorna <code>null</code>
     *
     * @param name O nome do produto a ser procurado
     * @return Objeto do tipo <code>Product</code>
     */
    public Product searchProductByName(String name) {
        String manipulatedName = name.toLowerCase().strip();

        for (Product product : this.products.keySet()) {
            if (product.getName().toLowerCase().equals(manipulatedName)) {
                return product;
            }
        }

        return null;
    }

    /**
     * Adiciona uma quantidade especifica de produtos do tipo
     * <code>Product</code> ao HashMap
     * <code><HashMap>products</code> da filial.
     *
     * @param product          Produto a ser adicionado a filial.
     * @param productsQuantity Quantidade de produtos a serem adicionados a filial.
     */
    public void addProduct(Product product, Integer productsQuantity) {
        this.products.put(product, productsQuantity);
    }

    /**
     * Remove um produto do HashMap <code>products</code> da filial.
     *
     * @param product Produto a ser removido da filial.
     * @return <code>true</code> se o produto foi removido com sucesso,
     *         <code>false</code> caso contrario.
     */
    public boolean removeProduct(Product product) {
        if (this.isAuthenticated) {
            if (this.products.containsKey(product)) {
                this.products.remove(product);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove uma quantidade especifica de produtos do HashMap <code>products</code>
     * da filial.
     * <p>
     * Caso a quantidade de <code>product</code> seja maior que
     * <code>quantity</code>,
     * decrementa a quantidade em <code>quantity</code>.
     * <p>
     * Caso contrario, remove <code>product</code> de
     * <code><HashMap>products</code>.
     *
     * @param product  Produto a ser decrementado.
     * @param quantity Quantidade a ser decrementada.
     * @return <code>true</code> se o produto foi removido com sucesso,
     *         <code>false</code> caso contrario.
     */
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

    /**
     * Realiza a compra de um produto por um cliente.
     * Apos a compra, a quantidade de produtos comprados é decrementada
     * e o produto é adicionado a lista de produtos comprados do cliente.
     *
     * @param client  Cliente que esta comprando o produto.
     * @param product Produto a ser comprado.
     * @see Branch#removeProduct(Product, Integer)
     */
    public void buyProduct(Client client, Product product) {
        removeProduct(product, 1);
        client.addProductToPurchasedProducts(product);
    }

    /**
     * Realiza o login de um gerente na area administrativa da filial.
     *
     * @param password Senha de acesso do gerente.
     * @return <code>true</code> se a senha estiver correta,
     *         <code>false</code> caso contrario.
     */
    public Boolean login(String password) {
        if (this.password.equals(password)) {
            this.isAuthenticated = true;
        }

        return this.isAuthenticated;
    }

    /**
     * Realiza o logout de um gerente na area administrativa da filial.
     */
    public void logout() {
        this.isAuthenticated = false;
    }

    /**
     * Retorna os produtos de um objeto <code>Branch</code>
     *
     * @return HashMap<Product, Integer>
     */
    public HashMap<Product, Integer> getProducts() {
        return this.products;
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
