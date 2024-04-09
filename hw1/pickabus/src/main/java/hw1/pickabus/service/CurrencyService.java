package hw1.pickabus.service;

import hw1.pickabus.model.CurrencyCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CurrencyService {

    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${currencyapi.apikey}")
    private String apiKey;

    private static final String BASE_URL = "https://api.currencyapi.com/v3/latest";
    public final Map<String, CurrencyCacheEntry> cache = new ConcurrentHashMap<>();
    private static final long TTL = 60; // Cache TTL in minutes

    public BigDecimal convertCurrency(String from, String to, BigDecimal amount) {
        // Attempt to use cached rates if available
        CurrencyCacheEntry cachedEntry = cache.getOrDefault(from, null);
        if (cachedEntry != null && !cachedEntry.isExpired() && cachedEntry.getRates().containsKey(to)) {
            BigDecimal rate = cachedEntry.getRates().get(to);
            return rate.multiply(amount);
        }

        // Fetch new rates if not cached or cache is expired
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = String.format("%s?apikey=%s&base_currency=%s&currencies=%s", BASE_URL, apiKey, from, to);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("data");
        if (responseData != null && responseData.containsKey(to)) {
            Map<String, BigDecimal> fetchedRates = new HashMap<>();
            responseData.forEach((key, valueMap) -> {
                Map<String, Object> valueDetails = (Map<String, Object>) valueMap;
                fetchedRates.put(key, new BigDecimal(valueDetails.get("value").toString()));
            });

            // Update the cache
            cache.put(from, new CurrencyCacheEntry(fetchedRates, LocalDateTime.now().plusMinutes(TTL)));

            // Return the requested conversion
            BigDecimal rate = fetchedRates.get(to);
            return rate.multiply(amount);
        }

        return BigDecimal.ZERO; // Return zero if conversion cannot be performed
    }
}
