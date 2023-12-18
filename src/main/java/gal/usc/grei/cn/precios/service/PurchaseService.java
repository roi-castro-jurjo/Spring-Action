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
     * Creates a new purchase in the database, given the purchase details.
     *
     * @param purchase The Purchase object containing details of the new purchase.
     * @return An Optional containing the created Purchase if successful, or an empty Optional if validation fails.
     */
    Optional<Purchase> create(Purchase purchase);


    /**
     * Handles the scenario where a payment for a purchase fails.
     * Updates the status of the provided purchase to FAILED and saves the changes to the database.
     *
     * @param purchase The Purchase object whose payment has failed.
     */
    void handlePaymentFailure(Purchase purchase);
}
