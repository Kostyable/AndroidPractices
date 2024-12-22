package ru.mirea.blinnikovkm.domain.domain.models;

import java.util.HashMap;
import java.util.Map;

public class Weather {
    private final double temperature;
    private final String description;
    private final double windSpeed;
    private final int windDirection;
    private int humidity;
    private int pressure;

    public Weather(double temperature, String description, double windSpeed, int windDirection, int humidity, int pressure) {
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return translateDescription(this.description);
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return translateWindDirection(this.windDirection);
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return (int) Math.ceil(this.pressure / 1.33);
    }

    public String formatWeather() {
        return String.format(
                "Погода:\nТемпература: %.1f°C\nОписание: %s\nВетер: %.1f м/с (%s)\nВлажность: %d%%\nДавление: %d мм рт. ст.",
                getTemperature(), getDescription(), getWindSpeed(), getWindDirection(), getHumidity(), getPressure()
        );
    }

    public static String translateWindDirection(int degrees) {
        String[] directions = {"С", "С-В", "В", "Ю-В", "Ю", "Ю-З", "З", "С-З"};
        return directions[(int) Math.round(((double) degrees % 360) / 45) % 8];
    }

    private static String translateDescription(String description) {
        Map<String, String> translations = new HashMap<>();
        translations.put("clear", "ясно");
        translations.put("clouds", "облачно");
        translations.put("rain", "дождь");
        translations.put("thunderstorm", "гроза");
        translations.put("snow", "снег");
        translations.put("mist", "туман");

        return translations.getOrDefault(description.toLowerCase(), "неизвестно");
    }
}
