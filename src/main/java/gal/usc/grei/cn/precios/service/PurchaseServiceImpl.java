package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    /**
     * Constructor of the class
     * @param purchaseRepository Reference to PurchaseRepository
     */
    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Purchase> get(String id) {
        return purchaseRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Purchase> create(Purchase purchase) {
        if(purchase.getId() == null || purchase.getId().isEmpty()) {
            return Optional.of(purchaseRepository.insert(purchase));
        }
        return Optional.empty();
    }
}
