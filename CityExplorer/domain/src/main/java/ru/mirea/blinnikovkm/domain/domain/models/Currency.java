package ru.mirea.blinnikovkm.domain.domain.models;

public class Currency {
    private int id;
    private String name;
    private String code;
    private double exchangeRate;

    public Currency() {}

    public Currency(int id, String name, String code, double exchangeRate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
