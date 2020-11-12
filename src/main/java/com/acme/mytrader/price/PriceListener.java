package com.acme.mytrader.price;

public interface PriceListener {
    void priceUpdate(String security, double price);
    void addSecurityTrigger(String name, double triggerPrice, int buyVolume);
}
