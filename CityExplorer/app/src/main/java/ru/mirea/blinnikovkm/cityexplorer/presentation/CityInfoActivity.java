package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.data.data.repository.RepositoryFactory;

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
                new CityInfoViewModelFactory(RepositoryFactory.getCityRepository(this))
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
                } else {
                    cityImageView.setImageResource(R.drawable.default_city_image);
                }
            }
        });
    }
}
