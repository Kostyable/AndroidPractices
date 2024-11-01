package ru.mirea.blinnikovkm.domain.domain.repository;

import ru.mirea.blinnikovkm.domain.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}