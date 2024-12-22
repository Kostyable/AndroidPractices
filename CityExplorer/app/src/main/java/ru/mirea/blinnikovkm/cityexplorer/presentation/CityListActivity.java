package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.data.data.repository.RepositoryFactory;
import ru.mirea.blinnikovkm.domain.domain.models.City;

public class CityListActivity extends AppCompatActivity {
    private CityListViewModel cityListViewModel;
    private RecyclerView recyclerView;
    private CityListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        cityListViewModel = new ViewModelProvider(
                this,
                new CityListViewModelFactory(RepositoryFactory.getCityRepository(this))
        ).get(CityListViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CityListAdapter(city ->
                Toast.makeText(this, "Selected city: " + city.getName(), Toast.LENGTH_SHORT).show()
        );
        recyclerView.setAdapter(adapter);

        observeViewModel();
    }

    private void observeViewModel() {
        cityListViewModel.getCities().observe(this, this::updateCityList);
    }

    private void updateCityList(List<City> cities) {
        adapter.submitList(cities);
    }
}
