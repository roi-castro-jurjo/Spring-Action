package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.domain.criteria.StockSearchCriteria;
import gal.usc.grei.cn.precios.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    private final PriceRepository priceRepository;
    private final MongoTemplate mongoTemplate;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Constructor of the class
     * @param priceRepository Instance of the PriceRepository class
     */
    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, MongoTemplate mongoTemplate){
        this.priceRepository = priceRepository;
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Price> getBySymbol(String symbol, Pageable pageable) {
        return priceRepository.findBySymbol(symbol, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Price> getStocks(StockSearchCriteria criteria, Pageable pageable) {
        Query query = new Query().with(pageable);
        System.out.println(criteria);

        if (criteria.getHighGreater() != null) {
            query.addCriteria(Criteria.where("high").gt(criteria.getHighGreater()));
        }
        if (criteria.getHighLess() != null) {
            query.addCriteria(Criteria.where("high").lt(criteria.getHighLess()));
        }
        if (criteria.getLowGreater() != null) {
            query.addCriteria(Criteria.where("low").gt(criteria.getLowGreater()));
        }
        if (criteria.getLowLess() != null) {
            query.addCriteria(Criteria.where("low").lt(criteria.getLowLess()));
        }
        if (criteria.getVolumeGreater() != null) {
            query.addCriteria(Criteria.where("volume").gt(criteria.getVolumeGreater()));
        }
        if (criteria.getVolumeLess() != null) {
            query.addCriteria(Criteria.where("volume").lt(criteria.getVolumeLess()));
        }
        if (criteria.getCloseGreater() != null) {
            query.addCriteria(Criteria.where("close").gt(criteria.getCloseGreater()));
        }
        if (criteria.getCloseLess() != null) {
            query.addCriteria(Criteria.where("close").lt(criteria.getCloseLess()));
        }
        if (criteria.getOpenGreater() != null) {
            query.addCriteria(Criteria.where("open").gt(criteria.getOpenGreater()));
        }
        if (criteria.getOpenLess() != null) {
            query.addCriteria(Criteria.where("open").lt(criteria.getOpenLess()));
        }
        if (isValidDate(criteria.getDate())) {
            query.addCriteria(Criteria.where("date").is(criteria.getDate()));
        }
        if (criteria.getSymbol() != null) {
            query.addCriteria(Criteria.where("symbol").is(criteria.getSymbol()));
        }

        List<Price> stocks = mongoTemplate.find(query, Price.class);
        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Price.class);

        return new PageImpl<>(stocks, pageable, total);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Price> getAllPrices(Pageable pageable) {
        return priceRepository.findAll(pageable);
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


}
