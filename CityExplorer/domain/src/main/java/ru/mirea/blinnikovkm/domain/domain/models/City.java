package ru.mirea.blinnikovkm.domain.domain.models;

import java.util.Objects;

public class City {
    private final int id;
    private final String name;
    private final String countryName;
    private final String description;
    private final String imagePath;

    public City(int id, String name, String countryName, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.countryName = countryName;
        this.description = description;
        this.imagePath = imagePath;
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

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
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
