package ru.mirea.blinnikovkm.mireaproject.ui.audiochanger;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AudioChangerViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AudioChangerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is microphone fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}