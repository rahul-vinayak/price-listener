package com.acme.mytrader.price;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.acme.mytrader.execution.ExecutionService;
import org.apache.commons.lang3.StringUtils;


/**
 * I am making a few assumptions here in the code as:
 * 1. Security is actually the name of the stock and the price update sets the price for the security(stock)
 * 2. I have added addSecurityTrigger method to PriceListener interface
 * 3. Tested by PriceListenerImplTest.java
 */
public class PriceListenerImpl implements PriceListener {

    private final ExecutionService executionService;
    private List<SecurityTrigger> securityTriggerList = new ArrayList<>();

    public PriceListenerImpl(ExecutionService executionService) {
        this.executionService = executionService;
    }

    /**
     * Method called by the PriceSource to check for the price trigger and trigger a BUY
     * @param security name of the security
     * @param price new price updated by the PriceSource
     */
    @Override
    public void priceUpdate(String security, double price) {
        securityTriggerList.stream()
                .filter(priceTriggerMatch(security, price))
                .forEach(executeStockBuy(price));
    }

    /**
     * This method is used to add the security trigger objects which listener will listen for
     * @param name name of the security added to the listener
     * @param triggerPrice price below which a buy trigger needs to be set
     * @param buyVolume volume of stocks to buy when trigger met
     */
    @Override
    public void addSecurityTrigger(String name, double triggerPrice, int buyVolume) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Security name cant be null");
        }
        securityTriggerList.add(new SecurityTrigger(name, triggerPrice, buyVolume));
    }

    public List<SecurityTrigger> getSecurityTriggerList() {
        return securityTriggerList;
    }

    private Consumer<SecurityTrigger> executeStockBuy(double price) {
        return s -> executionService.buy(s.getSecurityName(), price, s.getBuyVolume());
    }

    private Predicate<SecurityTrigger> priceTriggerMatch(String security, double price) {
        return s -> s.getSecurityName().equals(security) && price < s.getTriggerPrice();
    }
}
