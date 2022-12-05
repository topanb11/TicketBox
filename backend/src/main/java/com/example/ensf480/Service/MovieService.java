package com.example.ensf480.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.MovieDao;
import com.example.ensf480.Model.Movie;

// Class for MovieService
@Service
public class MovieService {
		private final MovieDao movieDao;

		// Injection dependency
		@Autowired
		public MovieService(@Qualifier("PostgresMovieDao")MovieDao movieDao) {
			this.movieDao = movieDao;
		} 

		// Getter to fetch all movies
		public List<Map<String,Object>> getAllMovie() {
			return movieDao.getAllMovies();
		}
 }
