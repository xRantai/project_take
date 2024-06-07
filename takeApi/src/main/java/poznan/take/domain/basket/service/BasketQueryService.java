package poznan.take.domain.basket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import poznan.take.domain.basket.BasketRepository;
import poznan.take.domain.basket.entity.BasketItem;
import poznan.take.domain.movie.service.MovieQueryService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketQueryService {
    private BasketRepository basketRepository;
    private MovieQueryService movieQueryService;

    public List<BasketItem> getContents() {
        return basketRepository.findAll();
    }

    public void addItem(Long id) {
        BasketItem newItem = new BasketItem();
        newItem.setMovie(movieQueryService.findById(id));

        basketRepository.save(newItem);
    }

    public void removeItem(long movieId) {
        Optional<Long> idInBasket = basketRepository.getIdInBasket(movieId);

        idInBasket.ifPresent(aLong -> basketRepository.delete(basketRepository.findById(aLong).orElseThrow()));
    }

    public boolean itemInBasket(long movieId) {
        Optional<Long> idInBasket = basketRepository.getIdInBasket(movieId);

        return idInBasket.isPresent();
    }
}
