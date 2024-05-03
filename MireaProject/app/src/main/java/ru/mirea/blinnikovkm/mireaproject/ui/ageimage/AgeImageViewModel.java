package ru.mirea.blinnikovkm.mireaproject.ui.ageimage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgeImageViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AgeImageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is camera fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}