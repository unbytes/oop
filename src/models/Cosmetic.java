package models;

/**
 * Representa um produto do tipo cosmético da rede de farmácia
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class Cosmetic extends Product {
    private String brand;
    private String type;
    private Boolean containsSunProtectionFactor;

    /**
     * Cria um objeto do tipo <code>Cosmetic</code>
     *
     * @param name
     * @param price
     * @param brand
     * @param type
     * @param containsSunProtectionFactor
     */
    public Cosmetic(String name, Integer price, String brand, String type, Boolean containsSunProtectionFactor) {
        super(name, price);
        this.brand = brand;
        this.type = type;
        this.containsSunProtectionFactor = containsSunProtectionFactor;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getContainsSunProtectionFactor() {
        return this.containsSunProtectionFactor;
    }

    public void setContainsSunProtectionFactor(Boolean containsSunProtectionFactor) {
        this.containsSunProtectionFactor = containsSunProtectionFactor;
    }
}
