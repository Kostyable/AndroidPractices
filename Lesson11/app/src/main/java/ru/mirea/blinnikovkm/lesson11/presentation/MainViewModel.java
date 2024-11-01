package ru.mirea.blinnikovkm.lesson11.presentation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.blinnikovkm.domain.domain.models.Movie;
import ru.mirea.blinnikovkm.domain.domain.repository.MovieRepository;
import ru.mirea.blinnikovkm.domain.domain.usecases.GetFavoriteMovieUseCase;
import ru.mirea.blinnikovkm.domain.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<String> favoriteMovie = new MutableLiveData<>();

    public MainViewModel(MovieRepository movieRepository) {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel created");
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }

    @Override
    protected void onCleared() {
        Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModel cleared");
        super.onCleared();
    }

    public void setText(Movie movie){
        Boolean result = new
                SaveMovieToFavoriteUseCase(movieRepository).execute(movie);
        favoriteMovie.setValue(result.toString());
    }
    public void getText(){
        Movie movie = new GetFavoriteMovieUseCase(movieRepository).execute();
        favoriteMovie.setValue(String.format("My favorite movie is %s",
                movie.getName()));
    }
}
