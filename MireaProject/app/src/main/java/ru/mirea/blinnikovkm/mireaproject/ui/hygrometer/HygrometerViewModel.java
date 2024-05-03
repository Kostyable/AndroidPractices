package ru.mirea.blinnikovkm.mireaproject.ui.hygrometer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HygrometerViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public HygrometerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hygrometer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}