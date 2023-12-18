package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class PurchaseOrchestratorImpl implements PurchaseOrchestrator{
    private final PurchaseService purchaseService;
    private final PaymentService paymentService;

    public PurchaseOrchestratorImpl(PurchaseService purchaseService, PaymentService paymentService){
        this.paymentService = paymentService;
        this.purchaseService = purchaseService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void executePurchase(Purchase purchase) {
        Optional<Purchase> createdPurchase = purchaseService.create(purchase);

        if (createdPurchase.isPresent()) {
            CompletableFuture<Boolean> paymentResult = paymentService.processPayment(createdPurchase.get());

            paymentResult.thenAccept(paymentSuccess -> {
                if (!paymentSuccess) {
                    purchaseService.handlePaymentFailure(createdPurchase.get());
                }
            });
        }
    }
}
