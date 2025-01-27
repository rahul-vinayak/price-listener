# Developer Programming Exercise

## User Story

As a trader I want to be able to monitor stock prices such that when they breach a trigger level orders can be executed automatically.

## Exercise

Given the following interface definitions (provided)

```
public interface ExecutionService {
    void buy(String securityName, double price, int volume);
    void sell(String securityName, double price, int volume);
}
```

```
public interface PriceListener {
    void priceUpdate(String securityName, double price);
}
```

```
public interface PriceSource {
    void addPriceListener(PriceListener listener);
    void removePriceListener(PriceListener listener);
}
```

Develop a basic implementation of the PriceListener interface that provides the following behaviour:

1. Connects to a PriceSource instance
1. Monitors price movements on a specified single stock (e.g. "IBM")
1. Executes a single "buy" instruction for a specified number of lots (e.g. 100) as soon as the price of that stock is seen to be below
a specified price (e.g. 55.0). Don�t worry what units that is in.

### Considerations

* Please "work out loud" and ask questions
* This is not a test of your API knowledge so feel free to check the web as reference
* There is no specific solution we are looking for

### Some libraries already available:

* Java 8
* JUnit 4
* Mockito
* EasyMock
* JMock

### Solution:

I have made a few assumptions here in the code as:

* Security is actually the name of the stock and the price update sets the price for the security(stock)
* I have added addSecurityTrigger method to PriceListener interface
* PriceListenerImpl is the implementation for the PriceListener
* Unit tests are in TradingStratgeyTest.java and PriceListenerImplTest.java


    
     * This method is used to add the security trigger objects which PriceListener will listen for
     * @param name name of the security added to the listener
     * @param triggerPrice price below which a buy trigger needs to be set
     * @param buyVolume volume of stocks to buy when trigger met
     */
     
    ```
    @Override
    public void addSecurityTrigger(String name, double triggerPrice, int buyVolume) {}
    ```
    
Feel free to contact me on rahul_vinayak@hotmail.com if you have any questions
