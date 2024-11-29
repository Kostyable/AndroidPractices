package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.data.data.storage.sharedprefs.UserSharedPrefs;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserSharedPrefs userSharedPrefs = new UserSharedPrefs(this);

        if (userSharedPrefs.isGuestMode()) {
            Toast.makeText(this, "You are in Guest Mode", Toast.LENGTH_SHORT).show();
        } else if (userSharedPrefs.isLoggedIn()) {
            Toast.makeText(this, "Welcome back, logged in user!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unknown state", Toast.LENGTH_SHORT).show();
        }
    }
}