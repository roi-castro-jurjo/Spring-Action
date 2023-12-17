package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;
import gal.usc.grei.cn.precios.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<List<Price>> getBySymbol(String symbol) {
        return priceRepository.findBySymbol(symbol);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }


}
