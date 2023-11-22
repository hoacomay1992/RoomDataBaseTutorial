package com.example.viewmodeltutorial.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
