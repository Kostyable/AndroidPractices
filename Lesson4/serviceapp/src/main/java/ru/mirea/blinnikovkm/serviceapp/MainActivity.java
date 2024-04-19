package ru.mirea.blinnikovkm.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ru.mirea.blinnikovkm.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int PermissionCode = 200;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.trackArtist.setText("Daft Punk");
        binding.trackTitle.setText("One More Time");
        binding.trackCover.setImageResource(R.drawable.track_cover);
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
        } else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений!");
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS, FOREGROUND_SERVICE}, PermissionCode);
        }
        binding.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    stopService(new Intent(MainActivity.this, PlayerService.class));
                    binding.buttonPlay.setImageResource(R.drawable.button_play);
                    isPlaying = false;
                } else {
                    Intent serviceIntent = new Intent(MainActivity.this, PlayerService.class);
                    ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
                    binding.buttonPlay.setImageResource(R.drawable.button_stop);
                    isPlaying = true;
                }

            }
        });
    }
}