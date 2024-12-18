package ru.mirea.blinnikovkm.domain.domain.models;

public class City {
    private String name;
    private String description;
    private String imageUrl;
    private float temperature;
    private String currencyCode;
    private int countryId;

    public City(String name, String description, String imageUrl, float temperature, String currencyCode, int countryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.temperature = temperature;
        this.currencyCode = currencyCode;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
