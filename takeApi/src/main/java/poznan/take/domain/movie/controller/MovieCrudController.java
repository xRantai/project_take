package poznan.take.domain.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import poznan.take.domain.movie.entity.Category;
import poznan.take.domain.movie.entity.Movie;
import poznan.take.domain.movie.service.MovieQueryService;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MovieCrudController {
    private final MovieQueryService movieQueryService;

    @GetMapping("/getAll")
    public Iterable<Movie> getAllMovies() {
        return movieQueryService.findAllMovies();
    }

    @GetMapping("/getByCategory/{category}")
    public List<Movie> getMoviesByCategory(@PathVariable Category category) {
        return movieQueryService.findByCategory(category);
    }

    @GetMapping("/getById/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieQueryService.findById(id);
    }

    @GetMapping("/getCategories")
    public List<Category> getCategories() {
        return movieQueryService.findAllCategories();
    }
}
