package ru.mirea.blinnikovkm.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class HeaderFragment extends Fragment {
    private ShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        Bundle args = getArguments();
        HashMap<String, String> countryData = (HashMap<String, String>) args.getSerializable("countryData");
        ArrayList<String> countries = new ArrayList<>(countryData.keySet());

        ListView countryListView = view.findViewById(R.id.countryListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, countries);
        countryListView.setAdapter(adapter);

        countryListView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            String selectedCountry = countries.get(position);
            viewModel.setSelectedItem(selectedCountry);
        });
    }
}
