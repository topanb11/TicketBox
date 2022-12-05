package com.example.ensf480.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.MovieDao;
import com.example.ensf480.Model.Movie;

@Service
public class MovieService {
		private final MovieDao movieDao;

		@Autowired
		public MovieService(@Qualifier("PostgresMovieDao")MovieDao movieDao) {
			this.movieDao = movieDao;
		} 

		public List<Map<String,Object>> getAllMovie() {
			return movieDao.getAllMovies();
		}
 }
