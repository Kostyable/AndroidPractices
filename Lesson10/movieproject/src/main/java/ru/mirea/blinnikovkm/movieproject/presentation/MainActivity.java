package ru.mirea.blinnikovkm.movieproject.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.movieproject.R;
import ru.mirea.blinnikovkm.movieproject.data.storage.MovieStorage;
import ru.mirea.blinnikovkm.movieproject.data.MovieRepositoryImpl;
import ru.mirea.blinnikovkm.movieproject.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.blinnikovkm.movieproject.domain.repository.MovieRepository;
import ru.mirea.blinnikovkm.movieproject.domain.models.Movie;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean result = movieRepository.saveMovie(new Movie(2, text.getText().toString()));
                textView.setText(String.format("Save result: %s", result));
            }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = movieRepository.getMovie();
                textView.setText(String.format("Favorite movie: %s", movie.getName()));
            }
        });
    }
}