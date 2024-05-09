package ru.mirea.blinnikovkm.employeedb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.idEditText.getText().toString();
                String name = binding.nameEditText.getText().toString();
                String powerpoints = binding.powerpointsEditText.getText().toString();
                if (id.isEmpty() || name.isEmpty() || powerpoints.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                Superhero superhero = new Superhero();
                superhero.id = Integer.parseInt(id);
                superhero.name = name;
                superhero.powerpoints = Integer.parseInt(powerpoints);
                App.getInstance().getDatabase().superheroDao().insert(superhero);
                Toast.makeText(MainActivity.this, "Данные успешно добавлены", Toast.LENGTH_SHORT).show();
            }
        });
        binding.getByIdButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String id = binding.idEditText.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Введите ID супергероя", Toast.LENGTH_SHORT).show();
                    return;
                }
                Superhero superhero = App.getInstance().getDatabase().superheroDao().getById(Integer.parseInt(id));
                if (superhero != null) {
                    binding.nameEditText.setText(superhero.name);
                    binding.powerpointsEditText.setText(Integer.toString(superhero.powerpoints));
                } else {
                    Toast.makeText(MainActivity.this, "Супергерой не найден", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.idEditText.getText().toString();
                String name = binding.nameEditText.getText().toString();
                String powerpoints = binding.powerpointsEditText.getText().toString();
                if (id.isEmpty() || name.isEmpty() || powerpoints.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                Superhero superhero = App.getInstance().getDatabase().superheroDao().getById(Integer.parseInt(id));
                if (superhero != null) {
                    superhero.name = name;
                    superhero.powerpoints = Integer.parseInt(powerpoints);
                    App.getInstance().getDatabase().superheroDao().update(superhero);
                    Toast.makeText(MainActivity.this, "Данные успешно обновлены", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Супергерой не найден", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}