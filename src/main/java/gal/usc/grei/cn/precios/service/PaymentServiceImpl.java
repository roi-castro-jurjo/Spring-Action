package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.OrderStatus;
import gal.usc.grei.cn.precios.domain.PaymentDetails;
import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PurchaseRepository purchaseRepository;
    private final Random random;

    @Autowired
    public PaymentServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.random = new Random();
    }

    @Async
    public CompletableFuture<Boolean> processPayment(Purchase purchase) {
        if (!isValidPaymentDetails(purchase.getPaymentDetails())) {
            return CompletableFuture.completedFuture(false);
        }

        try {
            int processingTime = 10000 + random.nextInt(20000);
            Thread.sleep(processingTime);

            boolean paymentSuccess = random.nextInt(10) != 0;
            if (paymentSuccess) {
                purchase.setStatus(OrderStatus.PAID);
            } else {
                purchase.setStatus(OrderStatus.FAILED);
            }
            purchaseRepository.save(purchase);

            return CompletableFuture.completedFuture(paymentSuccess);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture(false);
        }
    }

    private boolean isValidPaymentDetails(PaymentDetails details){
        if (details == null || details.getPaymentMethod() == null || details.getPaymentMethod().isEmpty() || details.getFullName() == null || details.getFullName().isEmpty()) {
            return false;
        }

        return isCardNumberValid(details.getCardNumber()) &&
                isCardExpiryDateValid(details.getCardExpiryDate()) &&
                isCardCVCValid(details.getCardCVC());
    }


    private boolean isCardNumberValid(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}") && !cardNumber.endsWith("3");
    }

    private boolean isCardExpiryDateValid(String cardExpiryDate) {
        if (cardExpiryDate == null || !cardExpiryDate.matches("\\d{2}/\\d{2}") || cardExpiryDate.length() != 5) {
            return false;
        }

        try {
            String[] parts = cardExpiryDate.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]) + 2000;

            LocalDate expiryDate = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());
            return !expiryDate.isBefore(LocalDate.now());
        } catch (DateTimeException e) {
            return false;
        }
    }

    private boolean isCardCVCValid(String cardCVC) {
        return cardCVC != null && cardCVC.matches("\\d{3}");
    }
}
