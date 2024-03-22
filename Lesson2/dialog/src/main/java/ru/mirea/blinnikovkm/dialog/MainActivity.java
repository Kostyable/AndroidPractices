package ru.mirea.blinnikovkm.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'Иду дальше\'",
                Toast.LENGTH_SHORT).show();
    }


    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'Нет\'",
                Toast.LENGTH_SHORT).show();
    }


    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'На паузе\'",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickShowTimeDialog(View view) {
        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment(this,
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker viewPicker, int hourOfDay, int minute) {
                String hourString = String.format("%02d", hourOfDay);
                String minuteString = String.format("%02d", minute);
                Snackbar.make(view, String.format("Вы выбрали время \'%s:%s\'", hourString,
                        minuteString), Snackbar.LENGTH_LONG).show();
            }
        }, 00, 00, true);

        timeDialogFragment.show();
    }

    public void onClickShowDateDialog(View view) {
        MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment(this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker viewPicker, int year, int month, int dayOfMonth) {
                String dayString = String.format("%02d", dayOfMonth);
                String monthString = String.format("%02d", month + 1);
                Snackbar.make(view, String.format("Вы выбрали дату \'%s.%s.%d\'", dayString,
                        monthString, year), Snackbar.LENGTH_LONG).show();
            }
        }, 2024, 0, 1);
        dateDialogFragment.show();
    }

    public void onClickShowProgressDialog(View view) {
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment(this);
        progressDialogFragment.setTitle("Progress Dialog");
        progressDialogFragment.setMessage("Загрузка...");
        progressDialogFragment.show();
    }
}