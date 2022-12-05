package com.example.ensf480.Api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensf480.Service.MovieService;

// API routes for Movies entity
@RequestMapping("api/v1/movie")
@RestController
@CrossOrigin
public class MovieController {

	// Instance of movieService to use methods
	private final MovieService movieService;

	// Dependency injection
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	// Route to fetch all movies
	@GetMapping("/all")
	public List<Map<String, Object>> getAllMovie() {
		return movieService.getAllMovie();
	}

}
