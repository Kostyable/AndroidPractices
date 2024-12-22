package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;
import ru.mirea.blinnikovkm.domain.domain.usecases.GetWeatherByCity;

public class CityInfoViewModelFactory implements ViewModelProvider.Factory {
    private final CityRepository cityRepository;
    private final GetWeatherByCity getWeatherByCity;

    public CityInfoViewModelFactory(CityRepository cityRepository, GetWeatherByCity getWeatherByCity) {
        this.cityRepository = cityRepository;
        this.getWeatherByCity = getWeatherByCity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CityInfoViewModel.class)) {
            return (T) new CityInfoViewModel(cityRepository, getWeatherByCity);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}