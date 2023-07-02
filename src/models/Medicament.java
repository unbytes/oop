package models;

/**
 * Representa um produto do tipo medicamento da rede de farmácia
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class Medicament extends Product {
    private String boxColor;
    private Integer dosageMg;
    private Integer minimumAge;

    /**
     * Cria um objeto do tipo <code>Medicament</code>
     *
     * @param name
     * @param price
     * @param boxColor
     * @param dosageMg
     * @param minimumAge
     */
    public Medicament(String name, Integer price, String boxColor, Integer dosageMg, Integer minimumAge) {
        super(name, price);
        this.boxColor = boxColor;
        this.dosageMg = dosageMg;
        this.minimumAge = minimumAge;
    }

    public String getBoxColor() {
        return this.boxColor;
    }

    public void setBoxColor(String boxColor) {
        this.boxColor = boxColor;
    }

    public Integer getDosageMg() {
        return this.dosageMg;
    }

    public void setDosageMg(Integer dosageMg) {
        this.dosageMg = dosageMg;
    }

    public Integer getMinimumAge() {
        return this.minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }
}
