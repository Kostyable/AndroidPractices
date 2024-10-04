package ru.mirea.blinnikovkm.movieproject.domain.models;

public class Movie {
    private int id;
    private String name;

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттер для id
    public int getId() {
        return id;
    }

    // Геттер для name
    public String getName() {
        return name;
    }
}
