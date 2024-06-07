package poznan.take.domain.basket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poznan.take.domain.basket.entity.BasketItem;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<BasketItem, Long> {
    @Query("SELECT bi.id FROM BasketItem bi JOIN bi.movie m WHERE m.id = :movieId")
    Optional<Long> getIdInBasket(@Param("movieId") long movieId);
}
