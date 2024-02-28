package lab2_1stocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockmarketService stockmarketService;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void testGetTotalValue() {
        
        Stock stock1 = new Stock("AAPL", 10);
        Stock stock2 = new Stock("GOOGL", 5);

        when(stockmarketService.lookUpPrice("AAPL")).thenReturn(150.0);
        when(stockmarketService.lookUpPrice("GOOGL")).thenReturn(1000.0);

        portfolio.addStock(stock1);
        portfolio.addStock(stock2);

        double totalValue = portfolio.getTotalValue();

        assertEquals(150.0 * 10 + 1000.0 * 5, totalValue, "The total value calculated is incorrect");
        
        verify(stockmarketService).lookUpPrice("AAPL");
        verify(stockmarketService).lookUpPrice("GOOGL");
    }
}
