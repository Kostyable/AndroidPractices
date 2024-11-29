package ru.mirea.blinnikovkm.data.data.mappers;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.domain.domain.models.City;

public class CityMapper {
    public static City mapToDomain(CityEntity cityEntity) {
        return new City(
                cityEntity.getName(),
                cityEntity.getDescription(),
                cityEntity.getImageUrl(),
                cityEntity.getTemperature(),
                cityEntity.getCurrencyCode(),
                cityEntity.getCountryId()
        );
    }

    public static CityEntity mapToData(City city) {
        return new CityEntity(
                city.getName(),
                city.getDescription(),
                city.getImageUrl(),
                city.getTemperature(),
                city.getCurrencyCode(),
                city.getCountryId()
        );
    }
}
