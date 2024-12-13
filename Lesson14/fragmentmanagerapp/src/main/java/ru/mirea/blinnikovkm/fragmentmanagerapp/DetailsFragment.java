package ru.mirea.blinnikovkm.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;

public class DetailsFragment extends Fragment {
    private ShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        Bundle args = getArguments();
        HashMap<String, String> countryData = (HashMap<String, String>) args.getSerializable("countryData");

        TextView detailTextView = view.findViewById(R.id.detailTextView);

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), selectedCountry -> {
            String details = countryData.get(selectedCountry);
            detailTextView.setText("Страна: " + selectedCountry + "\n" + details);
        });
    }
}
