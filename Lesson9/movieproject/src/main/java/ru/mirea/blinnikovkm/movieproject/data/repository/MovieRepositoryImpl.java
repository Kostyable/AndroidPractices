package ru.mirea.blinnikovkm.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.blinnikovkm.movieproject.domain.models.Movie;
import ru.mirea.blinnikovkm.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String PREFS_NAME = "favorite_movie_prefs";
    private static final String KEY_MOVIE_ID = "movie_id";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MOVIE_ID, movie.getId());
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        return editor.commit();  // Возвращаем результат commit
    }

    @Override
    public Movie getMovie() {
        int movieId = sharedPreferences.getInt(KEY_MOVIE_ID, -1);
        String movieName = sharedPreferences.getString(KEY_MOVIE_NAME, null);

        if (movieId != -1 && movieName != null) {
            return new Movie(movieId, movieName);  // Если фильм найден, возвращаем его
        }
        return new Movie(-1, "No favorite movie saved");  // Если нет данных, возвращаем пустой фильм
    }
}