package ru.mirea.blinnikovkm.data.data.repository;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class RepositoryFactory {
    private static AuthRepository authRepository;

    public static AuthRepository getAuthRepository() {
        if (authRepository == null) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            authRepository = new AuthRepositoryImpl(firebaseAuth);
        }
        return authRepository;
    }
}
