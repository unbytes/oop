package models;

/**
 * Representa um produto, de forma abstrata, na da rede de farmácia
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public abstract class Product {
    protected String name;
    protected Integer price;

    /**
     * Cria um objeto do tipo <code>Product</code>
     *
     * @param name  <code>String</code> com o nome do produto
     * @param price Preço do produto
     */
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
        return String.format("""
                Nome: %s
                Preço: %d
                """, this.name, this.price);
    }
}
