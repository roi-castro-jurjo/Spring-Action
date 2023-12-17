package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.domain.criteria.StockSearchCriteria;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    /**
     * Method that allows retrieving the price of a stock by its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @param pageable pagination information
     * @return The data of the stock with the provided parameters (if found).
     */
    Page<Price> getBySymbol(String symbol, Pageable pageable);


    /**
     * Retrieves a paginated list of stocks that match the specified search criteria.
     *
     * @param criteria filtering parameters.
     * @param pageable pagination information
     * @return Page<Price> object containing the paginated list of stocks that match the given criteria.
     */
    Page<Price> getStocks(StockSearchCriteria criteria, Pageable pageable);

    /**
     * Method that allows retrieving all prices of all stocks.
     *
     * @param pageable pagination information
     * @return The data of all the stocks
     */
    Page<Price> getAllPrices(Pageable pageable);
}
