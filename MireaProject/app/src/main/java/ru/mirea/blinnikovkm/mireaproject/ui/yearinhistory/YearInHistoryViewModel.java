package ru.mirea.blinnikovkm.mireaproject.ui.yearinhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YearInHistoryViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public YearInHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is year in history fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}