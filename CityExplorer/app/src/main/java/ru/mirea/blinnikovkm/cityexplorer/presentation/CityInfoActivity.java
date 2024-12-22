package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.domain.domain.usecases.GetWeatherByCity;
import ru.mirea.blinnikovkm.data.data.repository.WeatherRepositoryImpl;
import ru.mirea.blinnikovkm.data.data.repository.CityRepositoryImpl;

public class CityInfoActivity extends AppCompatActivity {
    private CityInfoViewModel cityInfoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        int cityId = getIntent().getIntExtra("CITY_ID", -1);
        if (cityId == -1) {
            Toast.makeText(this, "City ID не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        cityInfoViewModel = new ViewModelProvider(
                this,
                new CityInfoViewModelFactory(
                        new CityRepositoryImpl(this),
                        new GetWeatherByCity(new WeatherRepositoryImpl())
                )
        ).get(CityInfoViewModel.class);

        observeCity(cityId);
    }


    private void observeCity(int cityId) {
        cityInfoViewModel.getCity(cityId).observe(this, city -> {
            if (city != null) {
                ((TextView) findViewById(R.id.city_name)).setText(city.getName());
                ((TextView) findViewById(R.id.country_name)).setText(city.getCountryName());
                ((TextView) findViewById(R.id.city_description)).setText(city.getDescription());

                ImageView cityImageView = findViewById(R.id.city_image);
                int imageResource = getResources().getIdentifier(city.getImagePath(), "drawable", getPackageName());
                if (imageResource != 0) {
                    cityImageView.setImageResource(imageResource);
                }

                observeWeather(city.getName(), city.getCountryCode(), "95656fdcf88ba161d6274a9bbaad1750", "metric");
            }
        });
    }

    private void observeWeather(String cityName, String countryCode, String apiKey, String units) {
        cityInfoViewModel.getWeatherForCity(cityName, countryCode, apiKey, units).observe(this, weather -> {
            if (weather != null) {
                ((TextView) findViewById(R.id.weather_info)).setText(weather.formatWeather());
            } else {
                ((TextView) findViewById(R.id.weather_info)).setText("Ошибка загрузки погоды");
            }
        });
    }
}