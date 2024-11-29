package ru.mirea.blinnikovkm.data.data.repository;

import ru.mirea.blinnikovkm.data.data.mappers.CityMapper;
import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CityDao;
import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CityRepositoryImpl implements CityRepository {

    private final CityDao cityDao;

    public CityRepositoryImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public List<City> getAllCities() {
        List<CityEntity> cityEntities = cityDao.getAllCities();
        return cityEntities.stream()
                .map(CityMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public City getCityByName(String cityName) {
        CityEntity cityEntity = cityDao.getCityByName(cityName);
        if (cityEntity != null) {
            return CityMapper.mapToDomain(cityEntity);
        }
        return null;
    }

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        List<CityEntity> cityEntities = cityDao.getCitiesByCountryId(countryId);
        return cityEntities.stream()
                .map(CityMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void addCity(City city) {
        CityEntity cityEntity = CityMapper.mapToData(city);
        cityDao.insert(cityEntity);
    }

    @Override
    public void deleteCityByName(String cityName) {
        cityDao.deleteByName(cityName);
    }
}
