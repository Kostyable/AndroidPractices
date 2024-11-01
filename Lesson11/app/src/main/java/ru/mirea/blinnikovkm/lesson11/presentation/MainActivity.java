package ru.mirea.blinnikovkm.lesson11.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.lesson11.R;
import ru.mirea.blinnikovkm.data.data.storage.MovieStorage;
import ru.mirea.blinnikovkm.data.data.repository.MovieRepositoryImpl;
import ru.mirea.blinnikovkm.data.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.blinnikovkm.domain.domain.repository.MovieRepository;
import ru.mirea.blinnikovkm.domain.domain.models.Movie;

public class MainActivity extends AppCompatActivity {
    private MainViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);

        Log.d(MainActivity.class.getSimpleName().toString(), "MainActivity created");
        // При повороте экрана с vm = new MainViewModel(): MainViewModel created
        vm = new ViewModelProvider(this, new ViewModelFactory(this)).get(MainViewModel.class);

        vm.getFavoriteMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.setText(new Movie(2, text.getText().toString()));
            }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.getText();
            }
        });
    }
}