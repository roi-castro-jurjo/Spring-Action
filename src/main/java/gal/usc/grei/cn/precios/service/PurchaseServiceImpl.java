package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.OrderStatus;
import gal.usc.grei.cn.precios.domain.PaymentDetails;
import gal.usc.grei.cn.precios.domain.Purchase;
import gal.usc.grei.cn.precios.domain.criteria.PurchaseSearchCriteria;
import gal.usc.grei.cn.precios.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    private final MongoTemplate mongoTemplate;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, MongoTemplate mongoTemplate){
        this.purchaseRepository = purchaseRepository;
        this.mongoTemplate = mongoTemplate;
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
    public Page<Purchase> getPurchases(PurchaseSearchCriteria criteria, Pageable pageable) {
        Query query = new Query().with(pageable);
        System.out.println(criteria);

        if (criteria.getDate() != null && isValidDate(criteria.getDate())) {
            query.addCriteria(Criteria.where("date").is(criteria.getDate()));
        }
        if (criteria.getSymbol() != null) {
            query.addCriteria(Criteria.where("symbol").is(criteria.getSymbol()));
        }
        if (criteria.getVolumeGreater() != null) {
            query.addCriteria(Criteria.where("volume").gt(criteria.getVolumeGreater()));
        }
        if (criteria.getVolumeLess() != null) {
            query.addCriteria(Criteria.where("volume").lt(criteria.getVolumeLess()));
        }
        if (criteria.getUnitGreater() != null) {
            query.addCriteria(Criteria.where("unit").gt(criteria.getUnitGreater()));
        }
        if (criteria.getUnitLess() != null) {
            query.addCriteria(Criteria.where("unit").lt(criteria.getUnitLess()));
        }
        if (criteria.getTotalGreater() != null) {
            query.addCriteria(Criteria.where("total").gt(criteria.getTotalGreater()));
        }
        if (criteria.getTotalLess() != null) {
            query.addCriteria(Criteria.where("total").lt(criteria.getTotalLess()));
        }

        List<Purchase> purchases = mongoTemplate.find(query, Purchase.class);
        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Purchase.class);

        return new PageImpl<>(purchases, pageable, total);
    }


    /**
     * Checks if a string represents a valid date in the "yyyy-MM-dd" format.
     *
     * @param dateStr The string representing the date to be validated.
     * @return true if the string represents a valid date, false if the string is not a valid date.
     */
    private boolean isValidDate(String dateStr) {
        if (dateStr == null){
            return false;
        } else {
            try {
                LocalDate.parse(dateStr, DATE_FORMATTER);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Purchase> create(Purchase purchase) {
        if (purchase.getId() != null && !purchase.getId().isEmpty()) {
            return Optional.empty();
        }

        purchase.setStatus(OrderStatus.PENDING);

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        purchase.setDate(currentDate);

        if (purchase.getSymbol() == null || purchase.getSymbol().isEmpty() ||
                purchase.getVolume() == null ||
                purchase.getUnit() == null ||
                purchase.getTotal() == null) {
            return Optional.empty();
        }

        return Optional.of(purchaseRepository.insert(purchase));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlePaymentFailure(Purchase purchase) {
        purchase.setStatus(OrderStatus.FAILED);

        purchaseRepository.save(purchase);
    }


}
