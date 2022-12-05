package com.example.ensf480.Dao;

import java.util.List;
import java.util.Map;

// Interface for Movie functions
public interface MovieDao {
	/**
	 * Helper function to get all movies
	 * @return List<Map<String, Object>> - List of movies
	 */
	public List<Map<String, Object>> getAllMovies();
}
