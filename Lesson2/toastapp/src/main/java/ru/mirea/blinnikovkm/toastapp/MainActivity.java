package ru.mirea.blinnikovkm.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToastMessage(View view) {
        EditText editText = findViewById(R.id.editText);
        int len = editText.getText().length();
        Toast toast =Toast.makeText(getApplicationContext(),
                "СТУДЕНТ № 4 ГРУППА БСБО-10-21 Количество символов - " + len,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}