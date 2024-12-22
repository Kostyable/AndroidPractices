package ru.mirea.blinnikovkm.domain.domain.repository;

import java.util.List;

import ru.mirea.blinnikovkm.domain.domain.models.City;

public interface CityRepository {
    List<City> getAllCities();
    City getCityById(int cityId);
}