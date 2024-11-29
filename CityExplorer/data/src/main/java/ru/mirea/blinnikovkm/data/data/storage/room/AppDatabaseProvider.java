package ru.mirea.blinnikovkm.data.data.storage.room;

import android.content.Context;

import androidx.room.Room;

import ru.mirea.blinnikovkm.data.data.storage.models.CityEntity;
import ru.mirea.blinnikovkm.data.data.storage.models.CountryEntity;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CityDao;
import ru.mirea.blinnikovkm.data.data.storage.room.dao.CountryDao;

public class AppDatabaseProvider {

    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "city_database")
                    .allowMainThreadQueries()
                    .build();
            populateDatabase();
        }
        return appDatabase;
    }

    private static void populateDatabase() {
        CountryDao countryDao = appDatabase.countryDao();

        CountryEntity usa = new CountryEntity("United States", "US", "https://example.com/flags/us.png");
        CountryEntity france = new CountryEntity("France", "FR", "https://example.com/flags/fr.png");
        CountryEntity germany = new CountryEntity("Germany", "DE", "https://example.com/flags/de.png");
        CountryEntity italy = new CountryEntity("Italy", "IT", "https://example.com/flags/it.png");
        CountryEntity spain = new CountryEntity("Spain", "ES", "https://example.com/flags/es.png");
        CountryEntity japan = new CountryEntity("Japan", "JP", "https://example.com/flags/jp.png");
        CountryEntity canada = new CountryEntity("Canada", "CA", "https://example.com/flags/ca.png");
        CountryEntity australia = new CountryEntity("Australia", "AU", "https://example.com/flags/au.png");
        CountryEntity india = new CountryEntity("India", "IN", "https://example.com/flags/in.png");
        CountryEntity brazil = new CountryEntity("Brazil", "BR", "https://example.com/flags/br.png");

        countryDao.insert(usa);
        countryDao.insert(france);
        countryDao.insert(germany);
        countryDao.insert(italy);
        countryDao.insert(spain);
        countryDao.insert(japan);
        countryDao.insert(canada);
        countryDao.insert(australia);
        countryDao.insert(india);
        countryDao.insert(brazil);

        CityDao cityDao = appDatabase.cityDao();

        cityDao.insert(new CityEntity("New York", "A major city in the United States known for its iconic skyline and culture.", "https://example.com/cities/nyc.jpg", 25.0f, "USD", 1));
        cityDao.insert(new CityEntity("Paris", "Capital of France, famous for its art, fashion, and landmarks.", "https://example.com/cities/paris.jpg", 18.0f, "EUR", 2));
        cityDao.insert(new CityEntity("Berlin", "Capital of Germany, known for its history and modern architecture.", "https://example.com/cities/berlin.jpg", 15.0f, "EUR", 3));
        cityDao.insert(new CityEntity("Rome", "The capital of Italy, famous for its ancient history and monuments.", "https://example.com/cities/rome.jpg", 20.0f, "EUR", 4));
        cityDao.insert(new CityEntity("Madrid", "Capital of Spain, known for its art, culture, and vibrant lifestyle.", "https://example.com/cities/madrid.jpg", 22.0f, "EUR", 5));
        cityDao.insert(new CityEntity("Tokyo", "Capital of Japan, famous for its technology, culture, and cuisine.", "https://example.com/cities/tokyo.jpg", 30.0f, "JPY", 6));
        cityDao.insert(new CityEntity("Toronto", "The largest city in Canada, known for its multiculturalism and landmarks.", "https://example.com/cities/toronto.jpg", 10.0f, "CAD", 7));
        cityDao.insert(new CityEntity("Sydney", "The largest city in Australia, known for its beaches and iconic Opera House.", "https://example.com/cities/sydney.jpg", 18.0f, "AUD", 8));
        cityDao.insert(new CityEntity("Mumbai", "The financial capital of India, known for its film industry and vibrant culture.", "https://example.com/cities/mumbai.jpg", 32.0f, "INR", 9));
        cityDao.insert(new CityEntity("Rio de Janeiro", "Known for its beaches, carnival, and the iconic Christ the Redeemer statue.", "https://example.com/cities/rio.jpg", 28.0f, "BRL", 10));
    }

}
