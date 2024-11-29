package ru.mirea.blinnikovkm.data.data.storage.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CityDao;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CountryDao;

@Database(entities = {CountryEntity.class, CityEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();
    public abstract CityDao cityDao();
}
