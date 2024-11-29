package ru.mirea.blinnikovkm.domain.domain.usecases;

import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class Register {
    private final AuthRepository authRepository;

    public Register(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.register(email, password, callback);
    }
}
