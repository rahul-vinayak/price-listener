package com.acme.mytrader.strategy;

import static org.mockito.Mockito.verify;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    private TradingStrategy tradingStrategy;

    @Mock
    private PriceSource mockPriceSource;

    @Mock
    private ExecutionService mockExecutionService;

    private PriceListener priceListener;

    @Test
    public void shouldAddPriceListener() {
        //given
        tradingStrategy = new TradingStrategy(mockPriceSource);
        priceListener = new PriceListenerImpl(mockExecutionService);
        //when
        tradingStrategy.addPriceListener(priceListener);
        //then
        verify(mockPriceSource).addPriceListener(priceListener);
    }

    @Test
    public void shouldRemovePriceListener() {
        //given
        tradingStrategy = new TradingStrategy(mockPriceSource);
        priceListener = new PriceListenerImpl(mockExecutionService);
        //when
        tradingStrategy.removePriceListener(priceListener);
        //then
        verify(mockPriceSource).removePriceListener(priceListener);
    }
}
