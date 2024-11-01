package ru.mirea.blinnikovkm.data.data.storage;

import ru.mirea.blinnikovkm.data.data.storage.models.Movie;

public interface MovieStorage {
 public Movie get();
 public boolean save(Movie movie);
}