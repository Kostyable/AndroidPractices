package ru.mirea.blinnikovkm.data.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CurrencyApi {
    @GET("exchangerates_data/latest")
    Call<CurrencyResponse> getExchangeRates(
            @Query("base") String baseCurrency,
            @Query("symbols") String symbols,
            @Header("apikey") String apiKey
    );
}

