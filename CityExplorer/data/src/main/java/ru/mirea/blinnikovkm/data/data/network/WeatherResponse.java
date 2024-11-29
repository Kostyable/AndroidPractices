package ru.mirea.blinnikovkm.data.data.network;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<WeatherCondition> weather;

    public Main getMain() {
        return main;
    }

    public List<WeatherCondition> getWeather() {
        return weather;
    }

    public static class Main {
        @SerializedName("temp")
        private float temperature;

        public float getTemperature() {
            return temperature;
        }
    }

    public static class WeatherCondition {
        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}
