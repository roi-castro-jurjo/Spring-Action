package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.domain.criteria.PurchaseSearchCriteria;
import gal.usc.grei.cn.precios.domain.criteria.StockSearchCriteria;
import gal.usc.grei.cn.precios.repository.PurchaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface PurchaseService {

    Optional<Purchase> get(String id);

    /**
     * Retrieves a paginated list of purchases that match the specified search criteria.
     *
     * @param criteria filtering parameters.
     * @param pageable pagination information.
     * @return An Optional containing the data of the purchase once inserted, including the generated id,
     *         or an empty Optional if the insertion is not successful due to validation failures.
     */
    public Page<Purchase> getPurchases(PurchaseSearchCriteria criteria, Pageable pageable);

    /**
     * Method that allows inserting a new purchase into the database.
     * @param purchase The data of the purchase to be inserted.
     * @return The data of the purchase once inserted, including the id.
     * @throws ResponseStatusException Exception thrown in case any incorrect information is provided.
     */
    Optional<Purchase> create(Purchase purchase);

    void handlePaymentFailure(Purchase purchase);
}
