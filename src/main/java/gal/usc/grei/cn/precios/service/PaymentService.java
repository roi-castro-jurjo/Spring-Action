package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;

import java.util.concurrent.CompletableFuture;

public interface PaymentService {

    /**
     * Asynchronously processes the payment for a given purchase. This method first validates the payment
     * details of the purchase. If valid, it simulates the payment process, which randomly determines whether
     * the payment is successful or not. The process includes a simulated delay to mimic real-world payment
     * processing times.
     *
     * @param purchase The purchase object containing the payment details to be processed.
     * @return A CompletableFuture<Boolean> that will indicate the success or failure
     *         of the payment processing.
     * @throws InterruptedException if the thread executing the payment processing is interrupted.
     */
    CompletableFuture<Boolean> processPayment(Purchase purchase);
}
