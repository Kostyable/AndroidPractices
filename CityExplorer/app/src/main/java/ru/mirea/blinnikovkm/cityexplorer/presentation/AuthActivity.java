package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.data.data.storage.sharedprefs.UserSharedPrefs;
import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;
import ru.mirea.blinnikovkm.domain.domain.usecases.Login;
import ru.mirea.blinnikovkm.domain.domain.usecases.Register;
import ru.mirea.blinnikovkm.data.data.repository.RepositoryFactory;

public class AuthActivity extends AppCompatActivity {
    private Login loginUseCase;
    private Register registerUseCase;

    private EditText emailInput;
    private EditText passwordInput;
    private EditText repeatPasswordInput;
    private Button actionButton;
    private TextView toggleAuthMode;
    private Button guestButton;
    private UserSharedPrefs userSharedPrefs;

    private boolean isRegisterMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        AuthRepository authRepository = RepositoryFactory.getAuthRepository();
        loginUseCase = new Login(authRepository);
        registerUseCase = new Register(authRepository);

        userSharedPrefs = new UserSharedPrefs(this);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        repeatPasswordInput = findViewById(R.id.repeat_password_input);
        actionButton = findViewById(R.id.action_button);
        toggleAuthMode = findViewById(R.id.toggle_auth_mode);
        guestButton = findViewById(R.id.guest_button);

        actionButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (isRegisterMode) {
                String repeatPassword = repeatPasswordInput.getText().toString();
                if (!password.equals(repeatPassword)) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                register(email, password);
            } else {
                login(email, password);
            }
        });

        toggleAuthMode.setOnClickListener(v -> toggleMode());
        guestButton.setOnClickListener(v -> continueAsGuest());
    }

    private void toggleMode() {
        if (isRegisterMode) {
            switchToLoginMode();
        } else {
            isRegisterMode = true;
            actionButton.setText("Register");
            repeatPasswordInput.setVisibility(View.VISIBLE);
            toggleAuthMode.setText("Already have an account? Login");
        }
    }

    private void register(String email, String password) {
        registerUseCase.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(AuthActivity.this, "Registration successful! Please log in.", Toast.LENGTH_SHORT).show();
                switchToLoginMode();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(AuthActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchToLoginMode() {
        isRegisterMode = false;
        actionButton.setText("Login");
        repeatPasswordInput.setVisibility(View.GONE);
        toggleAuthMode.setText("Don't have an account? Register");
    }

    private void login(String email, String password) {
        loginUseCase.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(AuthActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                userSharedPrefs.setLoggedIn(true);
                userSharedPrefs.setGuestMode(false);
                navigateToMainScreen();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(AuthActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void continueAsGuest() {
        Toast.makeText(this, "Continuing as Guest", Toast.LENGTH_SHORT).show();
        userSharedPrefs.setLoggedIn(false);
        userSharedPrefs.setGuestMode(true);
        navigateToMainScreen();
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

