package ru.mirea.blinnikovkm.domain.domain.usecases;

import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class Login {
    private final AuthRepository authRepository;

    public Login(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.login(email, password, callback);
    }
}
