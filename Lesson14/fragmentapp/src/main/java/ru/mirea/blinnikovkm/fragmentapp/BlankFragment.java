package ru.mirea.blinnikovkm.fragmentapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (getArguments() != null) {
            int numberStudent = getArguments().getInt("my_number_student");
            Log.d(BlankFragment.class.getSimpleName(), "Мой номер студента: " + numberStudent);
            TextView textView = view.findViewById(R.id.textView);
            textView.setText("Мой номер студента: " + numberStudent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(BlankFragment.class.getSimpleName(), "onCreateView");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}