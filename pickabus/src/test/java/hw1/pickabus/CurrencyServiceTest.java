package hw1.pickabus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hw1.pickabus.model.CurrencyCacheEntry;
import hw1.pickabus.service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testConvertCurrency_UsesCache() {
        // Setup the cache to have some pre-loaded rates
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("USD", BigDecimal.valueOf(1.1));
        CurrencyCacheEntry cacheEntry = new CurrencyCacheEntry(rates, LocalDateTime.now().plusMinutes(30));
        currencyService.cache.put("EUR", cacheEntry);

        // Perform conversion using the cached rate
        BigDecimal result = currencyService.convertCurrency("EUR", "USD", BigDecimal.valueOf(100));

        // Verify no API call was made since cache was used
        verify(restTemplate, never()).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(Map.class));
        assertEquals(0, BigDecimal.valueOf(110).compareTo(result));
    }

    @Test
    void testConvertCurrency_RefreshesExpiredCache() {
        // Assume the cache has expired rates
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("USD", BigDecimal.valueOf(1.0));
        CurrencyCacheEntry expiredCacheEntry = new CurrencyCacheEntry(rates, LocalDateTime.now().minusMinutes(30));
        currencyService.cache.put("EUR", expiredCacheEntry);

        // Mock the API response for refreshed rates
        Map<String, Object> apiResponse = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> usdRate = new HashMap<>();
        usdRate.put("value", 1.2);
        data.put("USD", usdRate);
        apiResponse.put("data", data);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(ResponseEntity.ok(apiResponse));

        // Perform conversion which should refresh the cache
        BigDecimal result = currencyService.convertCurrency("EUR", "USD", BigDecimal.valueOf(100));

        // Verify API was called to refresh cache
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(Map.class));
        assertEquals(0, BigDecimal.valueOf(120).compareTo(result));
    }

}
