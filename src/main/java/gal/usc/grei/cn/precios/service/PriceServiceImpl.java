package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    private final PriceRepository priceRepository;


    /**
     * Constructor of the class
     * @param priceRepository Instance of the PriceRepository class
     */
    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Price> get(String symbol) {
        return priceRepository.findPrecioBySymbolAndDate(symbol, "2010-01-04");
    }
}
