package models;

public class Medicament extends Product {
    private String boxColor;
    private Integer dosageMl;
    private Integer minimumAge;

    public Medicament(String name, Integer price, String boxColor, Integer dosageMl, Integer minimumAge) {
        super(name, price);
        this.boxColor = boxColor;
        this.dosageMl = dosageMl;
        this.minimumAge = minimumAge;
    }

    public String getBoxColor() {
        return this.boxColor;
    }

    public void setBoxColor(String boxColor) {
        this.boxColor = boxColor;
    }

    public Integer getDosageMl() {
        return this.dosageMl;
    }

    public void setDosageMl(Integer dosageMl) {
        this.dosageMl = dosageMl;
    }

    public Integer getMinimumAge() {
        return this.minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }
}
