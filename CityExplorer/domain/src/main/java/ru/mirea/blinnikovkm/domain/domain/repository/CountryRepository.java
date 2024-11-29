package ru.mirea.blinnikovkm.domain.domain.repository;

import ru.mirea.blinnikovkm.domain.domain.models.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> getAllCountries();

    Country getCountryByCode(String countryCode);

    void addCountry(Country country);

    void deleteCountryByCode(String countryCode);
}
