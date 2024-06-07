package poznan.take.domain.movie.controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import poznan.take.domain.movie.entity.Category;
import poznan.take.domain.movie.entity.Movie;
import poznan.take.domain.movie.service.MovieQueryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class SampleDataGenerator {
    private MovieQueryService movieQueryService;
    final Random random = new Random();
    final Lorem lorem = new LoremIpsum();

    @PostConstruct
    private void initializeDatabase() {
        List<Movie> movies = new ArrayList<>();

        IntStream.range(1, 10)
                .forEach(i -> movies.add(createRandomMovie()));

        movieQueryService.saveAll(movies);
    }

    private Movie createRandomMovie() {
        Movie resultMovie = new Movie();

        resultMovie.setTitle(lorem.getTitle(2, 4));
        resultMovie.setPrice(generateRandomPrice());
        resultMovie.setCategories(generateRandomCategories());
        resultMovie.setProductionYear(random.nextInt(2024 - 2000) + 2000);
        resultMovie.setDescription(lorem.getWords(0, 30));

        return resultMovie;
    }

    private double generateRandomPrice() {
        double scale = Math.pow(10, 2);
        return Math.round(random.nextDouble() * 100 * scale) / scale;
    }

    private List<Category> generateRandomCategories() {
        List<Category> categories = new ArrayList<>();
        Arrays.stream(Category.values())
                .forEach(category -> {
                            if (random.nextBoolean())
                                categories.add(category);
                        }
                );
        return categories;
    }
}
