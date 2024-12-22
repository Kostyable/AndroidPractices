package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class AuthViewModelFactory implements ViewModelProvider.Factory {
    private final AuthRepository authRepository;

    public AuthViewModelFactory(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            return (T) new AuthViewModel(authRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}