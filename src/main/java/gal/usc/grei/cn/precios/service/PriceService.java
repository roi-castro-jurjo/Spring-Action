package gal.usc.grei.cn.precios.service;

import gal.usc.grei.cn.precios.domain.Price;

import java.util.Optional;

public interface PriceService {
    public Optional<Price> get(String symbol);
}
