package gal.usc.grei.cn.precios.repository;

import gal.usc.grei.cn.precios.domain.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends MongoRepository<Price, String> {
    @Query(value = "{'symbol': '?0', 'date': '?1'}")
    public Optional<Price> findPrecioBySymbolAndDate(String symbol, String date);

    public Optional<List<Price>> findBySymbol(String symbol);
}
