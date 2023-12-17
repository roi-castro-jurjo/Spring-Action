package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    /**
     * Method that allows retrieving the price of a stock by its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @return The data of the stock with the provided parameters (if found).
     */
    public Optional<List<Price>> getBySymbol(String symbol);
}
