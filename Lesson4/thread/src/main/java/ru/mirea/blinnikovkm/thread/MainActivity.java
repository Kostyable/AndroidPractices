package ru.mirea.blinnikovkm.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.mirea.blinnikovkm.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = binding.infoTextView;
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 10, НОМЕР ПО СПИСКУ: 4, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Маска");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());
        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы No %s номер по списку № %d ", numberThread, "БСБО-10-21", 4));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
                float pairs = Float.parseFloat(binding.pairsEditText.getText().toString());
                float days = Float.parseFloat(binding.daysEditText.getText().toString());
                float averagePairs = pairs / days;
                String result = String.format("%.2f", averagePairs);
                binding.resultTextView.setText("Среднее количество пар: " + result);
            }
        });
    }
}