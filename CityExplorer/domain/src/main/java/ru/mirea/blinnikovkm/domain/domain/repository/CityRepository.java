package ru.mirea.blinnikovkm.domain.domain.repository;

import ru.mirea.blinnikovkm.domain.domain.models.City;

import java.util.List;

public interface CityRepository {
    List<City> getAllCities();

    City getCityByName(String cityName);

    List<City> getCitiesByCountryId(int countryId);

    void addCity(City city);

    void deleteCityByName(String cityName);
}
