package gal.usc.grei.cn.precios.repository;

import gal.usc.grei.cn.precios.domain.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
