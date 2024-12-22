package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

public class CityListViewModel extends ViewModel {
    private final CityRepository cityRepository;
    private final MutableLiveData<List<City>> cities = new MutableLiveData<>();

    public CityListViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        loadCities();
    }

    public LiveData<List<City>> getCities() {
        return cities;
    }

    private void loadCities() {
        cities.setValue(cityRepository.getAllCities());
    }
}
