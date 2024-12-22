package ru.mirea.blinnikovkm.data.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.room.AppDatabaseProvider;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CityDao;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CountryDao;
import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

public class CityRepositoryImpl implements CityRepository {
    private final CityDao cityDao;
    private final CountryDao countryDao;

    public CityRepositoryImpl(Context context) {
        this.cityDao = AppDatabaseProvider.getInstance(context).cityDao();
        this.countryDao = AppDatabaseProvider.getInstance(context).countryDao();
    }

    @Override
    public List<City> getAllCities() {
        List<CityEntity> cityEntities = cityDao.getAllCities();
        List<City> cities = new ArrayList<>();
        for (CityEntity entity : cityEntities) {
            String countryName = countryDao.getAllCountries().stream()
                    .filter(country -> country.getId() == entity.getCountryId())
                    .findFirst()
                    .map(country -> country.getName())
                    .orElse("Unknown Country");
            cities.add(new City(entity.getId(), entity.getName(), countryName));
        }
        return cities;
    }
}

