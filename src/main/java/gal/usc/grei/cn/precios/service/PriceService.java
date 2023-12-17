package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    /**
     * Method that allows retrieving the price of a stock by its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @return The data of the stock with the provided parameters (if found).
     */
    Page<Price> getBySymbol(String symbol, Pageable pageable);

    /**
     * Method that allows retrieving all prices of all stocks.
     *
     * @return The data of all the stocks
     */
    Page<Price> getAllPrices(Pageable pageable);
}
