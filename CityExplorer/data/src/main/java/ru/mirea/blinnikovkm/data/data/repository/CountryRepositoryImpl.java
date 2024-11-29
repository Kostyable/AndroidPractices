package ru.mirea.blinnikovkm.data.data.repository;

import ru.mirea.blinnikovkm.data.data.mappers.CountryMapper;
import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CountryDao;
import ru.mirea.blinnikovkm.domain.domain.models.Country;
import ru.mirea.blinnikovkm.domain.domain.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CountryRepositoryImpl implements CountryRepository {

    private final CountryDao countryDao;

    public CountryRepositoryImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getAllCountries() {
        List<CountryEntity> countryEntities = countryDao.getAllCountries();
        return countryEntities.stream()
                .map(CountryMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Country getCountryByCode(String countryCode) {
        CountryEntity countryEntity = countryDao.getCountryByCode(countryCode);
        if (countryEntity != null) {
            return CountryMapper.mapToDomain(countryEntity);
        }
        return null;
    }

    @Override
    public void addCountry(Country country) {
        CountryEntity countryEntity = CountryMapper.mapToData(country);
        countryDao.insert(countryEntity);
    }

    @Override
    public void deleteCountryByCode(String countryCode) {
        countryDao.deleteByCode(countryCode);
    }
}
