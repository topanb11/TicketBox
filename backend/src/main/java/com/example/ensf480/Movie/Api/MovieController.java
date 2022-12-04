package com.example.ensf480.Movie.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensf480.Movie.Model.Movie;
import com.example.ensf480.Movie.Service.MovieService;

@RequestMapping("api/v1/movie")
@RestController
public class MovieController {

	private final MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/all")
	public List<Movie> getAllMovie() {
		return movieService.getAllMovie();
	}

}
