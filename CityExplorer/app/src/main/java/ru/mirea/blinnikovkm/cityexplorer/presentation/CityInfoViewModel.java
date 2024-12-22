package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.models.Weather;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;
import ru.mirea.blinnikovkm.domain.domain.repository.WeatherRepository;
import ru.mirea.blinnikovkm.domain.domain.usecases.GetWeatherByCity;

public class CityInfoViewModel extends ViewModel {

    private final CityRepository cityRepository;
    private final GetWeatherByCity getWeatherByCity;
    private final MediatorLiveData<City> cityLiveData = new MediatorLiveData<>();
    private final MediatorLiveData<Weather> weatherLiveData = new MediatorLiveData<>();

    public CityInfoViewModel(CityRepository cityRepository, GetWeatherByCity getWeatherByCity) {
        this.cityRepository = cityRepository;
        this.getWeatherByCity = getWeatherByCity;
    }

    public LiveData<City> getCity(int cityId) {
        City city = cityRepository.getCityById(cityId);
        cityLiveData.setValue(city);
        return cityLiveData;
    }

    public LiveData<Weather> getWeatherForCity(String cityName, String countryCode, String apiKey, String units) {
        MutableLiveData<Weather> weatherLiveData = new MutableLiveData<>();

        getWeatherByCity.execute(cityName, countryCode, apiKey, units, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(Weather weather) {
                weatherLiveData.postValue(weather);
            }

            @Override
            public void onFailure(Throwable throwable) {
                weatherLiveData.postValue(null);
            }
        });

        return weatherLiveData;
    }
}