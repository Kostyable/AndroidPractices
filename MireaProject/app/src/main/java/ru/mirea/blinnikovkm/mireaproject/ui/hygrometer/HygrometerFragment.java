package ru.mirea.blinnikovkm.mireaproject.ui.hygrometer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.mirea.blinnikovkm.mireaproject.databinding.FragmentHygrometerBinding;

public class HygrometerFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION = 300;
    private boolean isWork = false;
    private FragmentHygrometerBinding binding;
    private SensorManager sensorManager = null;
    private Sensor humiditySensor = null;
    private Button humidityButton = null;
    private TextView humidityTextView = null;
    private float humidity = -1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHygrometerBinding.inflate(inflater, container, false);
        sensorManager = (SensorManager)requireActivity().getSystemService(Context.SENSOR_SERVICE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorManager.registerListener(workingSensorEventListener, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        humidityButton = binding.humidityButton;
        humidityTextView = binding.humidityTextView;
        humidityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humidityTextView.setText(getCurrentHumidityDesc());
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String getCurrentHumidityDesc() {
        if (humidity >= 0 && humidity < 30) {
            return "Низкая (" + Math.floor(humidity) + "%)";
        }
        else if (humidity >= 30 && humidity < 60) {
            return "Нормальная (" + Math.floor(humidity) + "%)";
        }
        else if (humidity >= 60 && humidity <= 100) {
            return "Высокая (" + Math.floor(humidity) + "%)";
        }
        else {
            return "Не удалось определить";
        }
    }

    private final SensorEventListener workingSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        public void onSensorChanged(SensorEvent event) {
            humidity = event.values[0];
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(workingSensorEventListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(workingSensorEventListener, humiditySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}