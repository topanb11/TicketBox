package com.example.ensf480.Dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ensf480.Model.Movie;

@Repository("postgres")
public class MoviePostgresAccessService implements MovieDao{

		private JdbcTemplate jdbcTemplate;

		@Autowired
    public MoviePostgresAccessService(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

    @Override
    public List<Movie> getAllMovies() {
			final String QUERY = "SELECT * FROM movie;";
			List<Movie> movies = jdbcTemplate.query(QUERY, (resultSet, i) -> {
				return new Movie(
					UUID.fromString(resultSet.getString("id")),
					resultSet.getString("name")
				);
			});
			return movies;
		}
}
