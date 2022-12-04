package com.example.ensf480.Movie.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ensf480.Movie.Model.Movie;

@Repository("postgres")
public class MoviePostgresAccessService implements MovieDao{
    private static List<Movie> DB = new ArrayList<>();

    @Override
    public List<Movie> getAllMovies() {
        return DB;
    }
	
	
}
