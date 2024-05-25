package ru.mirea.blinnikovkm.mireaproject.ui.establishments;

public interface GeocodingListener {
    void onSuccess(String address);
    void onError(String errorMessage);
}