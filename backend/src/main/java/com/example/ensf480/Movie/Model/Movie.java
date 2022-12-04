package main.java.com.example.ensf480.Movie.Model;

import java.util.UUID;

public class Movie {
    private final UUID id;
    private String movieName;

    public Movie(int id, String movieName) {
        this.id = UUID.randomUUID();
        this.movieName = movieName;
    }

    public UUID getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
