package com.acme.mytrader.price;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

    @Mock
    private ExecutionService mockExecutionService;

    @InjectMocks
    private PriceListenerImpl priceListener;

    @Test
    public void shouldAddSecurityTrigger() {
        priceListener.addSecurityTrigger("IBM", 50, 10);

        assertThat(
                priceListener.getSecurityTriggerList(),
                contains(new SecurityTrigger("IBM", 50, 10)));
    }

    @Test
    public void shouldBuyStockWhenTriggerMet() {
        //given
        priceListener.addSecurityTrigger("IBM", 50.5, 10);
        priceListener.addSecurityTrigger("APPLE", 60, 10);
        //when
        priceListener.priceUpdate("IBM", 50.4);
        priceListener.priceUpdate("APPLE", 62);
        //then
        verify(mockExecutionService).buy("IBM", 50.4, 10);
        verifyNoMoreInteractions(mockExecutionService);
    }

    @Test
    public void shouldNotBuyStockWhenTriggerNotMet() {
        //given
        priceListener.addSecurityTrigger("IBM", 50, 10);
        //when
        priceListener.priceUpdate("IBM", 55);
        //then
        verifyZeroInteractions(mockExecutionService);
    }

    @Test
    public void shouldNotBuyStockWhenSecurityNotMatched() {
        //given
        priceListener.addSecurityTrigger("IBM", 50, 10);
        priceListener.addSecurityTrigger("APPLE", 60, 10);
        //when
        priceListener.priceUpdate("BMW", 10);
        //then
        verifyZeroInteractions(mockExecutionService);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenSecurityNameIsNull() {
        //given
        priceListener.addSecurityTrigger(null, 50, 10);
    }
}
