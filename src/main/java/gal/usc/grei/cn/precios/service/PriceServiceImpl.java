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
     * Method that allows retrieving the price of a stock by its symbol.
     *
     * @param symbol The symbol of the stock to retrieve
     * @return The data of the stock with the provided parameters (if found).
     */
    @Override
    public Optional<Price> get(String symbol) {
        return priceRepository.findPrecioBySymbolAndDate(symbol, "2010-01-04");
    }
}
