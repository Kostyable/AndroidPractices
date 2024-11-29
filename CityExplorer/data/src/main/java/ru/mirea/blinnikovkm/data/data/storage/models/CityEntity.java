package ru.mirea.blinnikovkm.data.data.storage.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities",
        foreignKeys = @ForeignKey(
                entity = CountryEntity.class,
                parentColumns = "id",
                childColumns = "countryId",
                onDelete = ForeignKey.CASCADE
        )
)
public class CityEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "temperature")
    private float temperature;

    @ColumnInfo(name = "currencyCode")
    private String currencyCode;

    @ColumnInfo(name = "countryId", index = true)
    private int countryId;

    public CityEntity(String name, String description, String imageUrl, float temperature, String currencyCode, int countryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.temperature = temperature;
        this.currencyCode = currencyCode;
        this.countryId = countryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}