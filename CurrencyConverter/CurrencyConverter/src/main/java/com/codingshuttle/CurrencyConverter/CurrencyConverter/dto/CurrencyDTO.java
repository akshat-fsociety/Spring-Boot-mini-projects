package com.codingshuttle.CurrencyConverter.CurrencyConverter.dto;

import lombok.*;

import java.util.Map;

@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class CurrencyDTO {
    private Map<String, Double> data;
    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }
}
