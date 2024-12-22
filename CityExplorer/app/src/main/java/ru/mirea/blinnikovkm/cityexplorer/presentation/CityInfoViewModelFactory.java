package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.domain.domain.repository.CityRepository;

public class CityInfoViewModelFactory implements ViewModelProvider.Factory {
    private final CityRepository cityRepository;

    public CityInfoViewModelFactory(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CityInfoViewModel.class)) {
            return (T) new CityInfoViewModel(cityRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
