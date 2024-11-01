package ru.mirea.blinnikovkm.data.data.repository;

import java.time.LocalDate;

import ru.mirea.blinnikovkm.data.data.storage.MovieStorage;
import ru.mirea.blinnikovkm.data.data.storage.models.Movie;
import ru.mirea.blinnikovkm.domain.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean saveMovie(ru.mirea.blinnikovkm.domain.domain.models.Movie movie){
        movieStorage.save(mapToStorage(movie));
        return true;
    }
    @Override
    public ru.mirea.blinnikovkm.domain.domain.models.Movie getMovie(){
        return mapToDomain(movieStorage.get());
    }
    private Movie mapToStorage(ru.mirea.blinnikovkm.domain.domain.models.Movie movie){
        String name = movie.getName();
        return new Movie(2, name, LocalDate.now().toString());
    }
    private ru.mirea.blinnikovkm.domain.domain.models.Movie mapToDomain(Movie movie){
        return new ru.mirea.blinnikovkm.domain.domain.models.Movie(movie.getId(),
                movie.getName());
    }
}