package com.e.dailyTasks;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CreateActionFragmentViewModel extends ViewModel {

    private MainModel model = new MainModel();
    private MutableLiveData<List<Action>> defaultActionLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Action>> getDefaultActionLiveData() {
        return defaultActionLiveData;
    }

    void getDefaultActions(){
        defaultActionLiveData.postValue(model.getDefaultActions());
    }
}
