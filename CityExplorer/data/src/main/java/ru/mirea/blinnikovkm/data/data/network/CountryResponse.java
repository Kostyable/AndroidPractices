package ru.mirea.blinnikovkm.data.data.network;

import com.google.gson.annotations.SerializedName;

public class CountryResponse {
    private String flagUrl;

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
