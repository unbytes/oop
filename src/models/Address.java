package models;

/**
 * Representa o endereço da filial.
 * Contem informações sobre cidade e região.
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 *
 */
public class Address {
    private String city;
    private String region;

    /**
     * Cria um objeto do tipo Address
     *
     * @param city   <code>String</code> A cidade da filial
     * @param region <code>String</code> A região da filial
     */
    public Address(String city, String region) {
        this.city = city;
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String newCity) {
        this.city = newCity;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String newRegion) {
        this.region = newRegion;
    }

    @Override
    public String toString() {
        return this.city + " | " + this.region + "\n";
    }
}
