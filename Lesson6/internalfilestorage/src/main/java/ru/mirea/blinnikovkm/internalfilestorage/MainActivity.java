package ru.mirea.blinnikovkm.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.blinnikovkm.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.memorableDateEditText.setText(getTextFromFile());
        binding.writeToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = binding.memorableDateEditText.getText().toString();
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                    Toast.makeText(MainActivity.this, "Дата сохранена", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}