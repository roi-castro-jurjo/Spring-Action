package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;

public interface PurchaseOrchestrator {

    /**
     * Executes the purchase process by following the Saga pattern. This method orchestrates two main stages:
     * creating the purchase order and processing the payment. If the payment process fails, a compensation
     * action is triggered.
     *
     * Stage 1: Creates a purchase order. If the creation is successful, it proceeds to the next stage.
     * Stage 2: Processes the payment asynchronously. If the payment fails, the method triggers a
     * compensation action to handle the failure.
     *
     * @param purchase The Purchase object to be processed.
     */
    void executePurchase(Purchase purchase);
}
