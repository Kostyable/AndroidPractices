package ru.mirea.blinnikovkm.mireaproject.ui.fileprocessing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FileProcessingViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public FileProcessingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is data processing fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}