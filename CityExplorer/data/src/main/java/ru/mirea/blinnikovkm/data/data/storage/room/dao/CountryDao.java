package ru.mirea.blinnikovkm.data.data.storage.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<CountryEntity> getAllCountries();

    @Query("SELECT * FROM countries WHERE code = :countryCode LIMIT 1")
    CountryEntity getCountryByCode(String countryCode);

    @Insert
    void insert(CountryEntity countryEntity);

    @Query("DELETE FROM countries WHERE code = :countryCode")
    void deleteByCode(String countryCode);

    @Query("SELECT * FROM countries WHERE id = :countryId LIMIT 1")
    CountryEntity getCountryById(int countryId);
}
