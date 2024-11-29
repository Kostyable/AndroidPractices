package ru.mirea.blinnikovkm.data.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryApi {
    @GET("countries/{countryCode}/flag")
    Call<CountryResponse> getFlag(@Path("countryCode") String countryCode);
}
