package ru.mirea.blinnikovkm.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.blinnikovkm.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();
        binding.editTextMirea.setText("Мой номер по списку No4");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(binding.editTextAge.getText().toString());
                String position = binding.editTextPosition.getText().toString();
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("age", age);
                bundle.putString("position", position);
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);

            }
        });
    }
}