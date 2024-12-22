package ru.mirea.blinnikovkm.domain.domain.usecases;

import ru.mirea.blinnikovkm.domain.domain.repository.WeatherRepository;

public class GetWeatherByCity {
    private final WeatherRepository weatherRepository;

    public GetWeatherByCity(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void execute(String cityName, String countryCode, String apiKey, String units, WeatherRepository.WeatherCallback callback) {
        weatherRepository.getWeatherByCity(cityName, countryCode, apiKey, units, callback);
    }
}
