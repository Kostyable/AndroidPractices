package ru.mirea.blinnikovkm.fragmentmanagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, String> countryData = new HashMap<>();
        countryData.put("США", "Столица: Вашингтон\nНаселение: 334 млн");
        countryData.put("Россия", "Столица: Москва\nНаселение: 146 млн");
        countryData.put("Германия", "Столица: Берлин\nНаселение: 84 млн");
        countryData.put("Франция", "Столица: Париж\nНаселение: 68 млн");
        countryData.put("Япония", "Столица: Токио\nНаселение: 123 млн");

        if (savedInstanceState == null) {
            HeaderFragment headerFragment = new HeaderFragment();
            DetailsFragment detailsFragment = new DetailsFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("countryData", countryData);
            headerFragment.setArguments(bundle);
            detailsFragment.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.headerContainerView, headerFragment, "header");
            transaction.add(R.id.detailsContainerView, detailsFragment, "details");
            transaction.commit();
        }
    }
}
