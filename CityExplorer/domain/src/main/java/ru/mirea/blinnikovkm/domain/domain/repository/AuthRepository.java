package ru.mirea.blinnikovkm.domain.domain.repository;

public interface AuthRepository {
    interface AuthCallback {
        void onSuccess();
        void onError(String errorMessage);
    }

    void login(String email, String password, AuthCallback callback);
    void register(String email, String password, AuthCallback callback);
    boolean isUserLoggedIn();
}
