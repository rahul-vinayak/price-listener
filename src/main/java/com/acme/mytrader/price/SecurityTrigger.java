package com.acme.mytrader.price;

import java.util.Objects;

public class SecurityTrigger {

    private final String securityName;
    private final double triggerPrice;
    private final int buyVolume;

    public SecurityTrigger(String securityName, double triggerPrice, int buyVolume) {
        this.securityName = securityName;
        this.triggerPrice = triggerPrice;
        this.buyVolume = buyVolume;
    }

    public String getSecurityName() {
        return securityName;
    }

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public int getBuyVolume() {
        return buyVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityTrigger that = (SecurityTrigger) o;
        return Double.compare(that.triggerPrice, triggerPrice) == 0 &&
                buyVolume == that.buyVolume &&
                securityName.equals(that.securityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityName, triggerPrice, buyVolume);
    }
}
