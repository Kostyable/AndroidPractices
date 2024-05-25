package ru.mirea.blinnikovkm.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import ru.mirea.blinnikovkm.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    new DownloadPageTask().execute("https://ipinfo.io/json");
                } else {
                    Toast.makeText(MainActivity.this, "Нет интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.loadTextView.setText("Загрузка...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(TAG, "Response: " + responseJson);
                if (responseJson.has("current_weather")) {
                    JSONObject weatherJson = responseJson.getJSONObject("current_weather");
                    Log.d(TAG, "Weather: " + weatherJson);
                    double temperature = weatherJson.getDouble("temperature");
                    binding.tempTextView.setText("Температура: " + temperature + "°C");
                    double windSpeed = weatherJson.getDouble("windspeed");
                    binding.windSpeedTextView.setText("Скорость ветра: " + windSpeed + " км/ч");
                    String windDirection = getWindDirection(weatherJson.getInt("winddirection"));
                    binding.windDirTextView.setText("Направление ветра: " + windDirection);
                }
                else {
                    String ip = responseJson.getString("ip");
                    binding.ipTextView.setText("IP: " + ip);
                    String city = responseJson.getString("city");
                    binding.cityTextView.setText("Город: " + city);
                    String region = responseJson.getString("region");
                    binding.regionTextView.setText("Регион: " + region);
                    String country = new Locale("",
                            responseJson.getString("country")).getDisplayCountry();
                    binding.countryTextView.setText("Страна: " + country);
                    String loc = responseJson.getString("loc");
                    String[] coordinates = loc.split(",");
                    if (coordinates.length == 2) {
                        String latitude = coordinates[0];
                        String longitude = coordinates[1];
                        String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                                + latitude + "&longitude=" + longitude + "&current_weather=true";
                        new DownloadPageTask().execute(weatherUrl);
                    }
                }
                binding.loadTextView.setText("");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read); }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage()+". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private String getWindDirection(int windDirection) {
        if (windDirection >= 337.5 || windDirection < 22.5) {
            return "С";
        } else if (windDirection >= 22.5 && windDirection < 67.5) {
            return "С-В";
        } else if (windDirection >= 67.5 && windDirection < 112.5) {
            return "В";
        } else if (windDirection >= 112.5 && windDirection < 157.5) {
            return "Ю-В";
        } else if (windDirection >= 157.5 && windDirection < 202.5) {
            return "Ю";
        } else if (windDirection >= 202.5 && windDirection < 247.5) {
            return "Ю-З";
        } else if (windDirection >= 247.5 && windDirection < 292.5) {
            return "З";
        } else {
            return "С-З";
        }
    }
}