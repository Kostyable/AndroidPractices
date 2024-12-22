package ru.mirea.blinnikovkm.domain.domain.repository;

import javax.security.auth.callback.Callback;

import ru.mirea.blinnikovkm.domain.domain.models.Weather;

public interface WeatherRepository {
    void getWeatherByCity(String cityName, String countryCode, String apiKey, String units, WeatherCallback callback);

    interface WeatherCallback {
        void onSuccess(Weather weather);
        void onFailure(Throwable throwable);
    }
}