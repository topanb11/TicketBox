package main.java.com.example.ensf480.Movie.Api;

import main.java.com.example.ensf480.Movie.Model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/movie")
@RestController
public class MovieController {

    @GetMapping("all")
    public List<Movie> getMovies() {
        return List.of(
                new Movie(
                 5,
                 "Black Panther"
                )
        );
    }

}
