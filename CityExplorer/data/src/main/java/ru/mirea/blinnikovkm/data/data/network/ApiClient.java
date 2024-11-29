package ru.mirea.blinnikovkm.data.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/";
    private static final String CURRENCY_BASE_URL = "https://api.apilayer.com/";
    private static final String COUNTRY_BASE_URL = "https://restcountries.com/";

    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WeatherApi getWeatherApi() {
        return createRetrofit(WEATHER_BASE_URL).create(WeatherApi.class);
    }

    public static CurrencyApi getCurrencyApi() {
        return createRetrofit(CURRENCY_BASE_URL).create(CurrencyApi.class);
    }

    public static CountryApi getCountryApi() {
        return createRetrofit(COUNTRY_BASE_URL).create(CountryApi.class);
    }
}
