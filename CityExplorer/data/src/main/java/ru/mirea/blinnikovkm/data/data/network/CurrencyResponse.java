package ru.mirea.blinnikovkm.data.data.network;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class CurrencyResponse {
    @SerializedName("rates")
    private Map<String, Float> rates;

    @SerializedName("base")
    private String baseCurrency;

    public Map<String, Float> getRates() {
        return rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }
}
