package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

public class CityListViewModel extends ViewModel {
    private final MediatorLiveData<List<City>> citiesLiveData = new MediatorLiveData<>();
    private final CityRepository cityRepository;

    public CityListViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        loadCities();
    }

    public LiveData<List<City>> getCities() {
        return citiesLiveData;
    }

    private void loadCities() {
        List<City> cities = cityRepository.getAllCities();
        citiesLiveData.setValue(cities);
    }
}
