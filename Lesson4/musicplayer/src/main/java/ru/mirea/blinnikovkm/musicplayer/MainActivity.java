package ru.mirea.blinnikovkm.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.blinnikovkm.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.trackArtist.setText("Daft Punk");
        binding.trackTitle.setText("One More Time");
        binding.trackCover.setImageResource(R.drawable.track_cover);
        int currentTime = 140;
        int totalTime = 320;
        binding.currentTime.setText(String.format("%02d:%02d", currentTime / 60, currentTime % 60));
        binding.totalTime.setText(String.format("%02d:%02d", totalTime / 60, totalTime % 60));
        binding.seekBar.setProgress((int) ((float) currentTime / totalTime * 100));
        binding.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    binding.buttonPlay.setImageResource(R.drawable.button_play);
                    isPlaying = false;
                    Log.d(MainActivity.class.getSimpleName(), "Track is paused");
                } else {
                    binding.buttonPlay.setImageResource(R.drawable.button_pause);
                    isPlaying = true;
                    Log.d(MainActivity.class.getSimpleName(), "Track is playing");
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isPlaying", isPlaying);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isPlaying = savedInstanceState.getBoolean("isPlaying");
        if (isPlaying) {
            binding.buttonPlay.setImageResource(R.drawable.button_pause);
            Log.d(MainActivity.class.getSimpleName(), "Track is playing");
        } else {
            binding.buttonPlay.setImageResource(R.drawable.button_play);
            Log.d(MainActivity.class.getSimpleName(), "Track is paused");
        }
    }
}