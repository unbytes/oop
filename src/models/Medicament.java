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
     * @param name       <code>String</code> Nome do produto
     * @param price      <code>Integer</code> Preço do produto
     * @param boxColor   <code>String</code> Cor da caixa do produto
     * @param dosageMg   <code>Integer</code> Dosagem do produto em mg
     * @param minimumAge <code>Integer</code> Idade mínima para consumir o produto
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
