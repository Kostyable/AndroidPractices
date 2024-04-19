package ru.mirea.blinnikovkm.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler =mainThreadHandler;
    }

    public void run() {
        Log.d(MyLooper.class.getSimpleName(), "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                int age = msg.getData().getInt("age");
                String position = msg.getData().getString("position");
                Log.d(MyLooper.class.getSimpleName(), String.format("MyLooper get message: %d, %s", age, position));
                try {
                    Thread.sleep(age * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("Мой возраст: %d лет, моя должность: %s", age, position));
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}