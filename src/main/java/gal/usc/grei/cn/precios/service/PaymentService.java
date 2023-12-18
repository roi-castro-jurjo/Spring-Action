package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;

import java.util.concurrent.CompletableFuture;

public interface PaymentService {
    CompletableFuture<Boolean> processPayment(Purchase purchase);
}
