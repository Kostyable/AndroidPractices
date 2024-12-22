package ru.mirea.blinnikovkm.domain.domain.models;

import java.util.Objects;

public class City {
    private final int id;
    private final String name;
    private final String countryName;
    private final String countryCode;
    private final String description;
    private final String imagePath;
    private String flagUrl;

    public City(int id, String name, String countryName, String countryCode, String description, String imagePath, String flagUrl) {
        this.id = id;
        this.name = name;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.description = description;
        this.imagePath = imagePath;
        this.flagUrl = flagUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getFlagUrl() {
        return flagUrl;
    }
    
    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name) &&
                Objects.equals(countryName, city.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryName);
    }
}
