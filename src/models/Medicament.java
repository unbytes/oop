package models;

public class Medicament extends Product {
    private String boxColor;
    private Integer dosageMg;
    private Integer minimumAge;

    public Medicament(String name, Integer price, Integer quantity, String boxColor, Integer dosageMg, Integer minimumAge) {
        super(name, price, quantity);
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
