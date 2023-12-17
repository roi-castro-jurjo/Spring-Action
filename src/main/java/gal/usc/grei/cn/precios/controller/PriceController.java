package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.domain.criteria.StockSearchCriteria;
import gal.usc.grei.cn.precios.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {
    // Reference to the PriceService class
    private final PriceService priceService;

    /**
     * Constructor of the class
     * @param priceService Instance of the PriceService class
     */
    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Method: GET
     * URL to access: /prices
     * Objective: Retrieve all stock prices in a paginated format with optional filtering criteria.
     *
     * @param criteria The criteria object containing various optional filtering parameters
     * @param date     Optional date filter. If provided, overrides 'date' in criteria.
     * @param symbol   Optional symbol filter. If provided, overrides 'symbol' in criteria.
     * @param page     Page number (0-indexed) for pagination.
     * @param size     Number of items per page for pagination.
     * @return         Paginated data of stocks, optionally filtered based on provided criteria.
     */

    @GetMapping(path = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Price>> getPrices(
            StockSearchCriteria criteria,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String symbol,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (date != null) {
            criteria.setDate(date);
        }
        if (symbol != null) {
            criteria.setSymbol(symbol);
        }

        Page<Price> prices = priceService.getStocks(criteria, PageRequest.of(page, size));
        return ResponseEntity.ok(prices);
    }

    /**
     * Method: GET
     * URL to access: /prices/{symbol}
     * Objective: retrieve the stock based on its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @param page Page number (0-indexed)
     * @param size Number of items per page
     * @return Paginated data of all the stocks.
     */
    @GetMapping(path = "{symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Price>> get(
            @PathVariable("symbol") String symbol,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Price> prices = priceService.getBySymbol(symbol, PageRequest.of(page, size));
        return ResponseEntity.ok(prices);
    }
}
