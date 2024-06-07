package poznan.take.domain.movie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poznan.take.domain.movie.MovieRepository;
import poznan.take.domain.movie.entity.Category;
import poznan.take.domain.movie.entity.Movie;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieQueryService {
    private final MovieRepository movieRepository;

    public Iterable<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> findByCategory(Category category) {
        return movieRepository.findByCategoriesContaining(category);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }

    public List<Category> findAllCategories() {
        return List.of(Category.values());
    }

    @Transactional
    public void saveAll(Iterable<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
