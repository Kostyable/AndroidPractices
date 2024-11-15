package ru.mirea.blinnikovkm.scrollviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout wrapper = findViewById(R.id.wrapper);

        BigInteger b = BigInteger.ONE;
        BigInteger ratio = BigInteger.valueOf(2);

        for (int i = 0; i < 100; i++) {
            View view = getLayoutInflater().inflate(R.layout.item, null, false);
            TextView text = (TextView) view.findViewById(R.id.textView);

            BigInteger progressionValue = b.multiply(ratio.pow(i));
            if (progressionValue.toString().length() > 19) {
                text.setTextSize(12);
            } else {
                text.setTextSize(16);
            }
            text.setText(String.format("Элемент %d: %s", i + 1, progressionValue));

            wrapper.addView(view);
        }
    }
}