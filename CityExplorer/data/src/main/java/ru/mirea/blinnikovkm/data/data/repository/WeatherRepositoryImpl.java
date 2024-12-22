package ru.mirea.blinnikovkm.data.data.repository;

import ru.mirea.blinnikovkm.data.data.network.WeatherResponse;
import ru.mirea.blinnikovkm.domain.domain.repository.WeatherRepository;
import ru.mirea.blinnikovkm.domain.domain.models.Weather;
import ru.mirea.blinnikovkm.data.data.network.WeatherApi;
import ru.mirea.blinnikovkm.data.data.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi weatherApi;

    public WeatherRepositoryImpl() {
        this.weatherApi = ApiClient.getWeatherApi();
    }

    @Override
    public void getWeatherByCity(String cityName, String countryCode, String apiKey, String units, WeatherCallback callback) {
        String query = cityName + (countryCode != null && !countryCode.isEmpty() ? "," + countryCode : "");
        Call<WeatherResponse> call = weatherApi.getWeather(query, apiKey, units);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    Weather weather = new Weather(
                            weatherResponse.getMain().getTemp(),
                            weatherResponse.getWeather().get(0).getMain(),
                            weatherResponse.getWind().getSpeed(),
                            weatherResponse.getWind().getDeg(),
                            weatherResponse.getHumidity(),
                            weatherResponse.getPressure()
                    );
                    callback.onSuccess(weather);
                } else {
                    callback.onFailure(new Exception("API Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
