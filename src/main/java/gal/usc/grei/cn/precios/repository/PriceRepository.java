package gal.usc.grei.cn.precios.repository;

import gal.usc.grei.cn.precios.domain.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
    @Query(value = "{'symbol': '?0', 'date': '?1'}")
    Optional<Price> findPrecioBySymbolAndDate(String symbol, String date);

    Optional<List<Price>> findBySymbol(String symbol);
}
