package hw1.pickabus.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class CurrencyCacheEntry {
    private Map<String, BigDecimal> rates;
    private LocalDateTime expiryTime;

    public CurrencyCacheEntry(Map<String, BigDecimal> rates, LocalDateTime expiryTime) {
        this.rates = rates;
        this.expiryTime = expiryTime;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}
