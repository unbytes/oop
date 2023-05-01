package models;

public class Address {
    private String city;
    private String region;

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
        return this.city + "; " + this.region + "\n";
    }
}
