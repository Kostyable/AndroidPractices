package ru.mirea.blinnikovkm.timeservice;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ru.mirea.blinnikovkm.timeservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private final String host = "time.nist.gov";
    private final int port = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.dateTextView.setText("Загрузка...");
                binding.timeTextView.setText("");
                GetTimeTask timeTask = new GetTimeTask();
                timeTask.execute();
            }
        });
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine();
                timeResult = reader.readLine();
                Log.d(TAG, timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String inputString = result.split(" ")[1] + " " + result.split(" ")[2];
            SimpleDateFormat inputFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            try {
                Date date = inputFormat.parse(inputString);
                TimeZone timeZone = TimeZone.getDefault();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.setTimeZone(timeZone);
                calendar.add(Calendar.MILLISECOND, timeZone.getOffset(calendar.getTimeInMillis()));
                String formattedDate = dateFormat.format(calendar.getTime());
                String formattedTime = timeFormat.format(calendar.getTime());
                String timeZoneOffset = timeZone.getDisplayName(false, TimeZone.SHORT);
                binding.dateTextView.setText(formattedDate);
                binding.timeTextView.setText(formattedTime + " (" + timeZoneOffset + ")");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}