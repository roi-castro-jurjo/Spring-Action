package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;

public interface PurchaseOrchestrator {
    void executePurchase(Purchase purchase);
}
