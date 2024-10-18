package ru.mirea.blinnikovkm.movieproject.domain.usecases;

import ru.mirea.blinnikovkm.movieproject.domain.models.Movie;
import ru.mirea.blinnikovkm.movieproject.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}