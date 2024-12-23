package ru.mirea.blinnikovkm.data.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;
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

    public List<City> getAllCities() {
        List<CityEntity> cityEntities = cityDao.getAllCities();
        List<City> cities = new ArrayList<>();
        for (CityEntity entity : cityEntities) {
            String countryName = countryDao.getAllCountries().stream()
                    .filter(country -> country.getId() == entity.getCountryId())
                    .findFirst()
                    .map(CountryEntity::getName)
                    .orElse("Unknown Country");
            String countryCode = countryDao.getAllCountries().stream()
                    .filter(country -> country.getId() == entity.getCountryId())
                    .findFirst()
                    .map(CountryEntity::getCode)
                    .orElse("UNKNOWN");

            String flagUrl = "https://flagcdn.com/w320/" + countryCode.toLowerCase() + ".png";

            cities.add(new City(
                    entity.getId(),
                    entity.getName(),
                    countryName,
                    countryCode,
                    entity.getDescription(),
                    entity.getImageUrl(),
                    flagUrl
            ));
        }
        return cities;
    }

    @Override
    public City getCityById(int cityId) {
        CityEntity cityEntity = cityDao.getCityById(cityId);
        if (cityEntity != null) {
            CountryEntity countryEntity = countryDao.getCountryById(cityEntity.getCountryId());
            String flagUrl = "https://flagcdn.com/w320/" + countryEntity.getCode().toLowerCase() + ".png";

            return new City(
                    cityEntity.getId(),
                    cityEntity.getName(),
                    countryEntity.getName(),
                    countryEntity.getCode(),
                    cityEntity.getDescription(),
                    cityEntity.getImageUrl(),
                    flagUrl
            );
        }
        return null;
    }
}