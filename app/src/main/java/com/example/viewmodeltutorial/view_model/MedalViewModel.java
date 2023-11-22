package com.example.viewmodeltutorial.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * để dữ liệu có thể được cập nhật theo thời gian thưc đến các observer, Dev cần biến dữ liệu đó thành các Subject. Các sebject
 * cần quan tâm đó là các giá trị của huy chương vậy nên các giá trị được khai báo trong ViewModel cần được bao trong lớp LiveData
 */
public class MedalViewModel extends ViewModel {

    private MutableLiveData<Integer> numberOfGoldMedal = new MutableLiveData<Integer>(0);
    private MutableLiveData<Integer> numberOfSilverMedal = new MutableLiveData<Integer>(0);
    private MutableLiveData<Integer> numberOfBronzeMedal = new MutableLiveData<Integer>(0);

    public MutableLiveData<Integer> getNumberOfGoldMedal() {
        return numberOfGoldMedal;
    }

    public MutableLiveData<Integer> getNumberOfSilverMedal() {
        return numberOfSilverMedal;
    }

    public MutableLiveData<Integer> getNumberOfBronzeMedal() {
        return numberOfBronzeMedal;
    }

    public int getNumberOfGoldMedalValue() {
        return numberOfGoldMedal.getValue();
    }

    public void setNumberOfGoldMedalValue(int number) {
        this.numberOfGoldMedal.setValue(number);
    }

    public int getNumberOfSilverMedalValue() {
        return numberOfSilverMedal.getValue();
    }

    public void setNumberOfSilverMedalValue(int number) {
        this.numberOfSilverMedal.setValue(number);
    }

    public int getNumberOfBronzeMedalValue() {
        return numberOfBronzeMedal.getValue();
    }

    public void setNumberOfBronzeMedalValue(int number) {
        this.numberOfBronzeMedal.setValue(number);
    }
}
