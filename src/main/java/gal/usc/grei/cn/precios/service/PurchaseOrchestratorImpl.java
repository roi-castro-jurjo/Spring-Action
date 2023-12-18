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
        // Etapa 1: Crear orden de compra
        Optional<Purchase> createdPurchase = purchaseService.create(purchase);

        if (createdPurchase.isPresent()) {
            // Etapa 2: Procesar pago
            CompletableFuture<Boolean> paymentResult = paymentService.processPayment(createdPurchase.get());

            paymentResult.thenAccept(paymentSuccess -> {
                if (!paymentSuccess) {
                    // Etapa de Compensación: Manejar fallo de pago
                    purchaseService.handlePaymentFailure(createdPurchase.get());
                }
            });
        }
    }
}
