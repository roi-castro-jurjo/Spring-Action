package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.domain.criteria.PurchaseSearchCriteria;
import gal.usc.grei.cn.precios.service.PriceService;
import gal.usc.grei.cn.precios.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    /**
     * Constructor of the class
     * @param purchaseService Instance of the PurchaseService class
     */
    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }



    /**
     * Method: GET
     * URL to access: /purchases
     * Retrieve all purchases in a paginated format with optional filtering criteria.

     * @param filter   The filter string representing the search criteria.
     * @param date     Optional date filter. If provided, overrides 'date' in criteria.
     * @param symbol   Optional symbol filter. If provided, overrides 'symbol' in criteria.
     * @param page     Page number (0-indexed) for pagination.
     * @param size     Number of items per page for pagination.
     * @param sort     Optional attribute name to sort by.
     * @param sortDir  Direction of sorting ('ASC' or 'DESC'). Used only if sort is provided.
     *
     * @return         Paginated data of purchases, optionally filtered based on provided criteria.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Purchase>> getPurchases(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String symbol,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "ASC") String sortDir) {

        PurchaseSearchCriteria criteria = parseFilter(filter);

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

        Page<Purchase> purchases = purchaseService.getPurchases(criteria, pageable);
        return ResponseEntity.ok(purchases);
    }


    /**
     * Parses a filter string to create and populate a PurchaseSearchCriteria object.
     *
     * @param filter The filter string representing the search criteria.
     * @return A populated PurchaseSearchCriteria object based on the provided filter string.
     */
    private PurchaseSearchCriteria parseFilter(String filter) {
        PurchaseSearchCriteria criteria = new PurchaseSearchCriteria();

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
                        case "volume" -> setCriteria(criteria, "volume", operator, value);
                        case "unit" -> setCriteria(criteria, "unit", operator, value);
                        case "total" -> setCriteria(criteria, "total", operator, value);
                    }
                }
            }
        }

        return criteria;
    }


    /**
     * Sets specific criteria for the given field in the PurchaseSearchCriteria object based on the provided operator and value.
     *
     * @param criteria The PurchaseSearchCriteria object where the criteria will be set.
     * @param field    The field of the StockSearchCriteria to be set.
     * @param operator The comparison operator, either '<' or '>'.
     * @param value    The value to be compared against, which is parsed as a double for price fields and as a long for volume.
     * @throws NumberFormatException If the value cannot be parsed to the appropriate numeric type.
     */
    private void setCriteria(PurchaseSearchCriteria criteria, String field, String operator, String value) {
        try {
            switch (field) {
                case "volume" -> {
                    long volumeValue = Long.parseLong(value);
                    if (operator.equals("<")) {
                        criteria.setVolumeLess(volumeValue);
                    } else if (operator.equals(">")) {
                        criteria.setVolumeGreater(volumeValue);
                    }
                }
                case "unit" -> {
                    float unitValue = Float.parseFloat(value);
                    if (operator.equals("<")) {
                        criteria.setUnitLess(unitValue);
                    } else if (operator.equals(">")) {
                        criteria.setUnitGreater(unitValue);
                    }
                }
                case "total" -> {
                    float totalValue = Float.parseFloat(value);
                    if (operator.equals("<")) {
                        criteria.setTotalLess(totalValue);
                    } else if (operator.equals(">")) {
                        criteria.setTotalGreater(totalValue);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Numeric format error for the field " + field + " with value " + value + ": " + e.getMessage());
        }
    }



    /**
     * Inserts a new purchase into the database. The purchase data is validated before insertion.
     *
     * @param purchase The data of the purchase to be inserted, expected to be valid.
     * @return A ResponseEntity containing either:
     *         - The newly created purchase and its access URL (HTTP 201 Created) if insertion is successful.
     *         - A bad request message (HTTP 400 Bad Request) if the data is invalid or insertion fails.
     */
    @PostMapping(path = "/purchases", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@Valid @RequestBody Purchase purchase) {
        Optional<Purchase> inserted = purchaseService.create(purchase);

        if (inserted.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(inserted.get().getId())
                    .toUri();
            return ResponseEntity.created(location).body(inserted.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid purchase data provided");
        }
    }

}
