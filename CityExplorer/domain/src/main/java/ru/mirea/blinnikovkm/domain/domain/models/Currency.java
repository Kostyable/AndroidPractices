package ru.mirea.blinnikovkm.domain.domain.models;

import java.util.Map;

public class Currency {
    private final String baseCurrency;
    private final Map<String, Double> exchangeRates;

    public Currency(String baseCurrency, Map<String, Double> exchangeRates) {
        this.baseCurrency = baseCurrency;
        this.exchangeRates = exchangeRates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public String formatCurrencyRates() {
        StringBuilder builder = new StringBuilder();
        builder.append("Основная валюта: ").append(baseCurrency).append("\n");
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            builder.append(entry.getKey()).append(": ").append(String.format("%.2f", entry.getValue())).append("\n");
        }
        return builder.toString();
    }
}
