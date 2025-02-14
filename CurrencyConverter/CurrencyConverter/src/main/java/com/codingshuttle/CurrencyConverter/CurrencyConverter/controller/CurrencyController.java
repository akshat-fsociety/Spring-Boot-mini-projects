package com.codingshuttle.CurrencyConverter.CurrencyConverter.controller;

import com.codingshuttle.CurrencyConverter.CurrencyConverter.clients.CurrencyClient;
import com.codingshuttle.CurrencyConverter.CurrencyConverter.dto.CurrencyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// LINK TO ACCESS
// http://localhost:8080/convertCurrency/getCurrency?fromCurrency=INR&toCurrency=USD&units=200.0
// 200.000000 INR = 2.305930 USD

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "convertCurrency")
public class CurrencyController {

    private final CurrencyClient currencyClient;

    @GetMapping("/getCurrency")
    public String convertCurrency( @RequestParam String fromCurrency,
                                   @RequestParam String toCurrency,
                                   @RequestParam double units){
        double result = currencyClient.getConvertedCurrency(fromCurrency,toCurrency,units);
        return String.format("%f %s = %f %s", units, fromCurrency, result, toCurrency);
    }


}
