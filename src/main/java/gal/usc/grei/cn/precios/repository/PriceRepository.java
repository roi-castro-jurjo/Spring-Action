package gal.usc.grei.cn.precios.repository;

import gal.usc.grei.cn.precios.domain.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PriceRepository extends MongoRepository<Price, String> {
    @Query(value = "{'symbol': '?0', 'date': '?1'}")
    public Optional<Price> findPrecioBySymbolAndDate(String simbolo, String fecha);
}
