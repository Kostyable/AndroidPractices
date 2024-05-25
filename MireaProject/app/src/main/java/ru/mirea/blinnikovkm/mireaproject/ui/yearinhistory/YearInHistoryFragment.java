package ru.mirea.blinnikovkm.mireaproject.ui.yearinhistory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentYearInHistoryBinding;

public class YearInHistoryFragment extends Fragment {
    private FragmentYearInHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYearInHistoryBinding.inflate(inflater, container, false);
        binding.getYearInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new YearInHistoryTask().execute();
            }
        });
        return binding.getRoot();
    }

    private class YearInHistoryTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.yearInfoTextView.setText("Загрузка...");
        }

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            try {
                int year = Integer.parseInt(binding.yearEditText.getText().toString());
                URL url = new URL(String.format("http://numbersapi.com/%d/year?json=true", year));
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream input = urlConnection.getInputStream();
                StringBuilder response = new StringBuilder();
                int data;
                while ((data = input.read()) != -1) {
                    response.append((char) data);
                }
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONObject responseJson = new JSONObject(result);
                    String yearInfo = responseJson.getString("text");
                    binding.yearInfoTextView.setText(yearInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Ошибка парсинга", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "Ошибка запроса", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}