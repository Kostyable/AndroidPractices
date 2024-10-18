package ru.mirea.blinnikovkm.movieproject.domain.repository;

import ru.mirea.blinnikovkm.movieproject.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}