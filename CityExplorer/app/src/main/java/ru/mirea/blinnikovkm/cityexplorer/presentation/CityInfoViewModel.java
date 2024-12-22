package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.blinnikovkm.domain.domain.models.City;
import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

public class CityInfoViewModel extends ViewModel {
    private final CityRepository cityRepository;
    private final MediatorLiveData<City> cityLiveData = new MediatorLiveData<>();

    public CityInfoViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public LiveData<City> getCity(int cityId) {
        City city = cityRepository.getCityById(cityId);
        cityLiveData.setValue(city);
        return cityLiveData;
    }
}
