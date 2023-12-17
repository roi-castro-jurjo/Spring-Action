package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * Objective: retrieve the stock based on its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @return If the symbol is valid, the data of the stock.
     */
    @GetMapping(path = "{symbol}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Price> get(@PathVariable("symbol") String symbol) {
        return ResponseEntity.of(priceService.get(symbol));
    }
}
