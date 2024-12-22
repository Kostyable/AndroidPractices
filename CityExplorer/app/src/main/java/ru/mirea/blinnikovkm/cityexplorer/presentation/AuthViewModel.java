package ru.mirea.blinnikovkm.cityexplorer.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(String email, String password) {
        isLoading.setValue(true);
        authRepository.login(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                isLoading.setValue(false);
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(String error) {
                isLoading.setValue(false);
                errorMessage.setValue(error);
            }
        });
    }

    public void register(String email, String password) {
        isLoading.setValue(true);
        authRepository.register(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                isLoading.setValue(false);
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(String error) {
                isLoading.setValue(false);
                errorMessage.setValue(error);
            }
        });
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }
}
