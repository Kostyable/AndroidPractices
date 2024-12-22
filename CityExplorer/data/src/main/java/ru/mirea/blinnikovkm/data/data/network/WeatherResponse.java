package ru.mirea.blinnikovkm.data.data.network;

import java.util.List;

public class WeatherResponse {
    private Main main;
    private List<WeatherDescription> weather;
    private Wind wind;
    private int humidity;
    private int pressure;

    public Main getMain() {
        return main;
    }

    public List<WeatherDescription> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public int getHumidity() {
        return main.humidity;
    }

    public int getPressure() {
        return main.pressure;
    }

    public static class Main {
        private double temp;
        private int pressure;
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public static class WeatherDescription {
        private String main;

        public String getMain() {
            return main;
        }
    }

    public static class Wind {
        private double speed;
        private int deg;

        public double getSpeed() {
            return speed;
        }

        public int getDeg() {
            return deg;
        }
    }
}
