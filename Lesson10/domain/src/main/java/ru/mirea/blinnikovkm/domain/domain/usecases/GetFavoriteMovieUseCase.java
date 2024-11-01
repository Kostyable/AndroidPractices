package ru.mirea.blinnikovkm.domain.domain.usecases;

import ru.mirea.blinnikovkm.domain.domain.models.Movie;
import ru.mirea.blinnikovkm.domain.domain.repository.MovieRepository;

public class GetFavoriteMovieUseCase {
    private MovieRepository movieRepository;
    public GetFavoriteMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute(){
        return movieRepository.getMovie();
    }
}