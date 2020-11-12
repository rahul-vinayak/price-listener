package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private final PriceSource priceSource;

    public TradingStrategy(PriceSource priceSource) {
        this.priceSource = priceSource;
    }

    public void addPriceListener(PriceListener priceListener) {
        this.priceSource.addPriceListener(priceListener);
    }

    public void removePriceListener(PriceListener priceListener) {
        this.priceSource.removePriceListener(priceListener);
    }
}
