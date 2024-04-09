package hw1.pickabus.controller;

import hw1.pickabus.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> convertCurrency(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("amount") BigDecimal amount) {
        BigDecimal convertedAmount = currencyService.convertCurrency(from, to, amount);
        return ResponseEntity.ok(convertedAmount);
    }
}
