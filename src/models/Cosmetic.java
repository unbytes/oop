package models;

public class Cosmetic {
    private String brand;
    private String type;
    private Boolean containsSunProtectionFactor;

    public Cosmetic(String brand, String type, Boolean containsSunProtectionFactor) {
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
