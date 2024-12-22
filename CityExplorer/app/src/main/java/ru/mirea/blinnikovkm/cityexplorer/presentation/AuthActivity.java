package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.data.data.repository.RepositoryFactory;
import ru.mirea.blinnikovkm.data.data.storage.sharedprefs.UserSharedPrefs;
import ru.mirea.blinnikovkm.domain.domain.repository.AuthRepository;

public class AuthActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

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

        authViewModel = new ViewModelProvider(this, new AuthViewModelFactory(authRepository))
                .get(AuthViewModel.class);

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

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isRegisterMode) {
                String repeatPassword = repeatPasswordInput.getText().toString();
                if (!password.equals(repeatPassword)) {
                    Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    return;
                }
                authViewModel.register(email, password);
            } else {
                authViewModel.login(email, password);
            }
        });

        toggleAuthMode.setOnClickListener(v -> toggleMode());
        guestButton.setOnClickListener(v -> continueAsGuest());

        observeViewModel();
    }

    private void toggleMode() {
        if (isRegisterMode) {
            switchToLoginMode();
        } else {
            isRegisterMode = true;
            actionButton.setText("Зарегистрироваться");
            repeatPasswordInput.setVisibility(View.VISIBLE);
            toggleAuthMode.setText("Есть аккаунт? Войдите");
        }
    }

    private void switchToLoginMode() {
        isRegisterMode = false;
        actionButton.setText("Войти");
        repeatPasswordInput.setVisibility(View.GONE);
        toggleAuthMode.setText("Нет аккаунта? Зарегистрируйтесь");
    }

    private void observeViewModel() {
        authViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading != null && isLoading) {
                    actionButton.setEnabled(false);
                    actionButton.setText("Загрузка...");
                } else {
                    actionButton.setEnabled(true);
                    actionButton.setText(isRegisterMode ? "Зарегистрироваться" : "Войти");
                }
            }
        });

        authViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(AuthActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        authViewModel.getLoginSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success != null && success) {
                    Toast.makeText(AuthActivity.this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
                    userSharedPrefs.setLoggedIn(true);
                    userSharedPrefs.setGuestMode(false);
                    navigateToMainScreen();
                }
            }
        });
    }

    private void continueAsGuest() {
        Toast.makeText(this, "Вы вошли как гость", Toast.LENGTH_SHORT).show();
        userSharedPrefs.setLoggedIn(false);
        userSharedPrefs.setGuestMode(true);
        navigateToMainScreen();
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(AuthActivity.this, CityListActivity.class);
        startActivity(intent);
        finish();
    }
}
