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
        CityDao cityDao = appDatabase.cityDao();

        if (countryDao.getAllCountries().isEmpty()) {
            CountryEntity usa = new CountryEntity("Соединенные Штаты", "US", "https://example.com/flags/us.png");
            CountryEntity france = new CountryEntity("Франция", "FR", "https://example.com/flags/fr.png");
            CountryEntity germany = new CountryEntity("Германия", "DE", "https://example.com/flags/de.png");
            CountryEntity italy = new CountryEntity("Италия", "IT", "https://example.com/flags/it.png");
            CountryEntity uk = new CountryEntity("Великобритания", "GB", "https://example.com/flags/gb.png");
            CountryEntity japan = new CountryEntity("Япония", "JP", "https://example.com/flags/jp.png");
            CountryEntity russia = new CountryEntity("Россия", "RU", "https://example.com/flags/ru.png");
            CountryEntity australia = new CountryEntity("Австралия", "AU", "https://example.com/flags/au.png");
            CountryEntity china = new CountryEntity("Китай", "CN", "https://example.com/flags/cn.png");
            CountryEntity brazil = new CountryEntity("Бразилия", "BR", "https://example.com/flags/br.png");

            countryDao.insert(usa);
            countryDao.insert(france);
            countryDao.insert(germany);
            countryDao.insert(italy);
            countryDao.insert(uk);
            countryDao.insert(japan);
            countryDao.insert(russia);
            countryDao.insert(australia);
            countryDao.insert(china);
            countryDao.insert(brazil);
        }

        if (cityDao.getAllCities().isEmpty()) {
            int usaId = countryDao.getCountryByCode("US").getId();
            int franceId = countryDao.getCountryByCode("FR").getId();
            int germanyId = countryDao.getCountryByCode("DE").getId();
            int italyId = countryDao.getCountryByCode("IT").getId();
            int ukId = countryDao.getCountryByCode("GB").getId();
            int japanId = countryDao.getCountryByCode("JP").getId();
            int russiaId = countryDao.getCountryByCode("RU").getId();
            int australiaId = countryDao.getCountryByCode("AU").getId();
            int chinaId = countryDao.getCountryByCode("CN").getId();
            int brazilId = countryDao.getCountryByCode("BR").getId();

            cityDao.insert(new CityEntity("Нью-Йорк", "Крупнейший город США, известный своим знаменитым горизонтом и культурой.", "new_york", usaId));
            cityDao.insert(new CityEntity("Париж", "Столица Франции, известная искусством, модой и достопримечательностями.", "paris", franceId));
            cityDao.insert(new CityEntity("Берлин", "Столица Германии, известная своей историей и современной архитектурой.", "berlin", germanyId));
            cityDao.insert(new CityEntity("Рим", "Столица Италии, знаменитая своей древней историей и памятниками.", "rome", italyId));
            cityDao.insert(new CityEntity("Лондон", "Столица Великобритании, известная своей историей, культурой и достопримечательностями.", "london", ukId));
            cityDao.insert(new CityEntity("Токио", "Столица Японии, знаменитая своей технологией, культурой и кухней.", "tokyo", japanId));
            cityDao.insert(new CityEntity("Москва", "Столица России, известная богатой историей и удивительной архитектурой.", "moscow", russiaId));
            cityDao.insert(new CityEntity("Сидней", "Крупнейший город Австралии, известный пляжами и знаменитым оперным театром.", "sydney", australiaId));
            cityDao.insert(new CityEntity("Шанхай", "Шумный мегаполис Китая, известный своим горизонтом и культурным наследием.", "shanghai", chinaId));
            cityDao.insert(new CityEntity("Рио-де-Жанейро", "Известен пляжами, карнавалом и знаменитой статуей Христа-Искупителя.", "rio", brazilId));
        }
    }
}
