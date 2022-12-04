package com.example.ensf480.Movie.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Movie.Dao.MovieDao;
import com.example.ensf480.Movie.Model.Movie;

@Service
public class MovieService {

	private final MovieDao movieDao;

	@Autowired
	public MovieService(@Qualifier("postgres")MovieDao movieDao) {
		this.movieDao = movieDao;
	} 

	public List<Movie> getAllMovie() {
		return movieDao.getAllMovies();
	}
 }
