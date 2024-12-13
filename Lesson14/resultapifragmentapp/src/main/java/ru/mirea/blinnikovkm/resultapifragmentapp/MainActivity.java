package ru.mirea.blinnikovkm.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements FragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().setFragmentResultListener(
                "requestKey",
                this,
                (requestKey, bundle) -> {
                    String result = bundle.getString("key");
                    Log.d(BottomSheetFragment.class.getSimpleName(), "I'm MainActivity " + result);
                }
        );
    }

    @Override
    public void sendResult(String message) {
        Log.d(MainActivity.class.getSimpleName(), "message: " + message);
    }
}