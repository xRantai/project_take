package poznan.take.domain.basket.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import poznan.take.domain.basket.entity.BasketItem;
import poznan.take.domain.basket.service.BasketQueryService;
import poznan.take.domain.movie.entity.Movie;

import java.util.List;

@RestController
@RequestMapping(value = "/basket")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BasketCrudController {
    private BasketQueryService basketQueryService;

    @GetMapping("/getContents")
    public List<Movie> getContents() {
        return basketQueryService.getContents().stream()
                .map(BasketItem::getMovie)
                .toList();
    }

    @GetMapping("/getTotal")
    public double getTotal() {
        return getContents().stream()
                .mapToDouble(Movie::getPrice)
                .sum();
    }

    @PostMapping("/add/{id}")
    public void addItem(@PathVariable long id) {
        if (basketQueryService.itemInBasket(id))
            return;

        basketQueryService.addItem(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeItem(@PathVariable long id) {
        if (!basketQueryService.itemInBasket(id))
            return;

        basketQueryService.removeItem(id);
    }
}
