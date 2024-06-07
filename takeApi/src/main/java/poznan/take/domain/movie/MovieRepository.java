package poznan.take.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import poznan.take.domain.movie.entity.Category;
import poznan.take.domain.movie.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByCategoriesContaining(Category category);
}
