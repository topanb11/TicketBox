package com.example.ensf480.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Implementation of methods from Movie interface
@Repository("PostgresMovieDao")
public class MoviePostgresAccessService implements MovieDao{
		// SQL query to fetch all movies and showtimes
		final String QUERY = "SELECT t1.*, t2.id AS showtimeID, t2.showtime FROM movie AS t1 JOIN showtime AS t2 ON t1.id::varchar = t2.movieid;";
		// Instance of JdbcTempalte to connect to database
		private JdbcTemplate jdbcTemplate;

		// Dependency Injection
		@Autowired
    public MoviePostgresAccessService(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		/**
		 * Function to fetch all movies
		 * @return - List of movies with showtimes embedded
		 */
    @Override
    public List<Map<String, Object>> getAllMovies() {
			// Original query to get all movies and merge the showtimes
			List<Map<String, Object>> movies = jdbcTemplate.queryForList(QUERY);
			List<Map<String, Object>> formattedMovies = new ArrayList<>();

			Map<String, Object> tmpMovie = new HashMap<String, Object>(movies.get(0));
			List<Object> tmpShowtimes = new ArrayList<>();

			// Iterate through movies and format movies to have all listed showtimes
			for (int i = 0; i < movies.size(); i++) {
				String currID = movies.get(i).get("id").toString();
				Map<String, Object> showtime = new HashMap<String,Object>();
				if ( !currID.equals(tmpMovie.get("id").toString())) {
					tmpMovie.remove("showtime");
					tmpMovie.remove("showtimeid");
					tmpMovie.put("showtimes", tmpShowtimes);
					formattedMovies.add(tmpMovie);
					tmpMovie = new HashMap<String, Object>(movies.get(i));
					tmpShowtimes = new ArrayList<>();

				}
				showtime.put("showtimeId", movies.get(i).get("showtimeId"));
				showtime.put("timestamp", movies.get(i).get("showtime"));
				tmpShowtimes.add(showtime);
			}
			return formattedMovies;
		}
}
