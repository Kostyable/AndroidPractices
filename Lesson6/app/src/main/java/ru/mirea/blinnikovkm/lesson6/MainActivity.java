package ru.mirea.blinnikovkm.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings",	Context.MODE_PRIVATE);
        binding.groupNumberEditText.setText(sharedPref.getString("groupNumber", ""));
        binding.listNumberEditText.setText(sharedPref.getString("listNumber", ""));
        binding.favouriteFilmEditText.setText(sharedPref.getString("favouriteFilm", ""));
        binding.saveValuesButton.setOnClickListener(new	View.OnClickListener() {
            @Override
            public void	onClick(View v)	{
                String groupNumber = binding.groupNumberEditText.getText().toString();
                String listNumber = binding.listNumberEditText.getText().toString();
                String favouriteFilm = binding.favouriteFilmEditText.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("groupNumber", groupNumber);
                editor.putString("listNumber", listNumber);
                editor.putString("favouriteFilm", favouriteFilm);
                editor.apply();
                Toast.makeText(MainActivity.this, "Значения сохранены", Toast.LENGTH_SHORT).show();
            }
        });
    }
}