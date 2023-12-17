package gal.usc.grei.cn.precios.controller;

import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.service.PriceService;
import gal.usc.grei.cn.precios.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
     * Method: POST
     * URL to access: /purchases
     * Objective: insert the purchase provided as a parameter.
     *
     * @param purchase the data of the purchase to insert
     * @return If the insertion is successful, the new purchase and the URL to access it.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> create(@Valid @RequestBody Purchase purchase) {
        Optional<Purchase> inserted = purchaseService.create(purchase);
        return ResponseEntity.created(URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/purchases/" + inserted.get().getId()))
                .body(inserted.get());
    }

}
