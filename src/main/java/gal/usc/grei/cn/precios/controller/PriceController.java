package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.domain.criteria.StockSearchCriteria;
import gal.usc.grei.cn.precios.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/prices")
public class PriceController {
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
     * Retrieve all stock prices in a paginated format with optional filtering criteria.

     * @param filter   The filter string representing the search criteria.
     * @param date     Optional date filter. If provided, overrides 'date' in criteria.
     * @param symbol   Optional symbol filter. If provided, overrides 'symbol' in criteria.
     * @param page     Page number (0-indexed) for pagination.
     * @param size     Number of items per page for pagination.
     * @param sort     Optional attribute name to sort by.
     * @param sortDir  Direction of sorting ('ASC' or 'DESC'). Used only if sort is provided.
     *
     * @return         Paginated data of stocks, optionally filtered based on provided criteria.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Price>> getPrices(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String symbol,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "ASC") String sortDir) {

        StockSearchCriteria criteria = parseFilter(filter);

        if (date != null) {
            criteria.setDate(date);
        }
        if (symbol != null) {
            criteria.setSymbol(symbol);
        }

        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            Sort.Direction direction = Sort.Direction.fromString(sortDir);
            pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Price> prices = priceService.getStocks(criteria, pageable);
        return ResponseEntity.ok(prices);
    }


    /**
     * Parses a filter string to create and populate a StockSearchCriteria object.
     *
     * @param filter The filter string representing the search criteria.
     * @return A populated StockSearchCriteria object based on the provided filter string.
     */
    private StockSearchCriteria parseFilter(String filter) {
        StockSearchCriteria criteria = new StockSearchCriteria();

        if (filter != null && !filter.isEmpty()) {
            String[] conditions = filter.split(",");

            Pattern pattern = Pattern.compile("(\\w+)([<>])([^,]+)");

            for (String condition : conditions) {
                Matcher matcher = pattern.matcher(condition.trim());
                if (matcher.matches()) {
                    String field = matcher.group(1);
                    String operator = matcher.group(2);
                    String value = matcher.group(3);

                    switch (field) {
                        case "high" -> setCriteria(criteria, "high", operator, value);
                        case "low" -> setCriteria(criteria, "low", operator, value);
                        case "volume" -> setCriteria(criteria, "volume", operator, value);
                        case "close" -> setCriteria(criteria, "close", operator, value);
                        case "open" -> setCriteria(criteria, "open", operator, value);
                    }
                }
            }
        }

        return criteria;
    }


    /**
     * Sets specific criteria for the given field in the StockSearchCriteria object based on the provided operator and value.
     *
     * @param criteria The StockSearchCriteria object where the criteria will be set.
     * @param field    The field of the StockSearchCriteria to be set.
     * @param operator The comparison operator, either '<' or '>'.
     * @param value    The value to be compared against, which is parsed as a double for price fields and as a long for volume.
     * @throws NumberFormatException If the value cannot be parsed to the appropriate numeric type.
     */
    private void setCriteria(StockSearchCriteria criteria, String field, String operator, String value) {
        try {
            switch (field) {
                case "high" -> {
                    double highValue = Double.parseDouble(value);
                    if (operator.equals("<")) {
                        criteria.setHighLess(highValue);
                    } else if (operator.equals(">")) {
                        criteria.setHighGreater(highValue);
                    }
                }
                case "low" -> {
                    double lowValue = Double.parseDouble(value);
                    if (operator.equals("<")) {
                        criteria.setLowLess(lowValue);
                    } else if (operator.equals(">")) {
                        criteria.setLowGreater(lowValue);
                    }
                }
                case "close" -> {
                    double closeValue = Double.parseDouble(value);
                    if (operator.equals("<")) {
                        criteria.setCloseLess(closeValue);
                    } else if (operator.equals(">")) {
                        criteria.setCloseGreater(closeValue);
                    }
                }
                case "open" -> {
                    double openValue = Double.parseDouble(value);
                    if (operator.equals("<")) {
                        criteria.setOpenLess(openValue);
                    } else if (operator.equals(">")) {
                        criteria.setOpenGreater(openValue);
                    }
                }
                case "volume" -> {
                    long volumeValue = Long.parseLong(value);
                    if (operator.equals("<")) {
                        criteria.setVolumeLess(volumeValue);
                    } else if (operator.equals(">")) {
                        criteria.setVolumeGreater(volumeValue);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Numeric format error for the field " + field + " with value " + value + ": " + e.getMessage());
        }
    }




    /**
     * Method: GET
     * URL to access: /prices/{symbol}
     * Retrieve the stock based on its symbol.
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
