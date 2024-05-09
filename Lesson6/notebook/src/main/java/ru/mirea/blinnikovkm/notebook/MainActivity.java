package ru.mirea.blinnikovkm.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.blinnikovkm.notebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.writeDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageWritable()) {
                    writeFileToExternalStorage();
                }
            }
        });
        binding.readDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable()) {
                    readFileFromExternalStorage();
                }
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void writeFileToExternalStorage() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, binding.fileNameEditText.getText() + ".txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
            output.write(String.valueOf(binding.quoteEditText.getText()));
            output.close();

        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    public void readFileFromExternalStorage() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, binding.fileNameEditText.getText() + ".txt");
        try	{
            FileInputStream fileInputStream	= new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            String text = lines.toString();
            if (text.length() > 2) {
                binding.quoteEditText.setText(text.substring(1, text.length() - 1));
            }
            Log.w("ExternalStorage", String.format("Read from file %s successful", lines.toString()));
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
        }
    }
}