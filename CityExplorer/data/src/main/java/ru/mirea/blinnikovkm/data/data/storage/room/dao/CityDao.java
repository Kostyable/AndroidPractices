package ru.mirea.blinnikovkm.data.data.storage.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities")
    List<CityEntity> getAllCities();

    @Query("SELECT * FROM cities WHERE name = :cityName LIMIT 1")
    CityEntity getCityByName(String cityName);

    @Query("SELECT * FROM cities WHERE countryId = :countryId")
    List<CityEntity> getCitiesByCountryId(int countryId);

    @Insert
    void insert(CityEntity cityEntity);

    @Query("DELETE FROM cities WHERE name = :cityName")
    void deleteByName(String cityName);
}
