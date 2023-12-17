package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Price;
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
     * Objective: retrieve all stock prices in a paginated format
     *
     * @param page Page number (0-indexed)
     * @param size Number of items per page
     * @return Paginated data of all the stocks.
     */
    @GetMapping(path = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Price>> getPricesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Price> prices = priceService.getAllPrices(PageRequest.of(page, size));
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
