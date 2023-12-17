package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.repository.PurchaseRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface PurchaseService {

    public Optional<Purchase> get(String id);

    /**
     * Method that allows inserting a new purchase into the database.
     * @param purchase The data of the purchase to be inserted.
     * @return The data of the purchase once inserted, including the id.
     * @throws ResponseStatusException Exception thrown in case any incorrect information is provided.
     */
    public Optional<Purchase> create(Purchase purchase);
}
