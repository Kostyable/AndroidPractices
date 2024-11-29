package ru.mirea.blinnikovkm.data.data.mappers;

import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;
import ru.mirea.blinnikovkm.domain.domain.models.Country;

public class CountryMapper {
    public static Country mapToDomain(CountryEntity countryEntity) {
        return new Country(
                countryEntity.getName(),
                countryEntity.getCode(),
                countryEntity.getFlagUrl()
        );
    }

    public static CountryEntity mapToData(Country country) {
        return new CountryEntity(
                country.getName(),
                country.getCode(),
                country.getFlagUrl() != null ? country.getFlagUrl() : ""
        );
    }
}
