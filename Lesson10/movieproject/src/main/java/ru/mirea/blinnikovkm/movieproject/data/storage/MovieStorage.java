package ru.mirea.blinnikovkm.movieproject.data.storage;

import ru.mirea.blinnikovkm.movieproject.data.storage.models.Movie;

public interface MovieStorage {
 public Movie get();
 public boolean save(Movie movie);
}