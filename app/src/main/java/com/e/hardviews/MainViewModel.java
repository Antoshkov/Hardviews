package com.e.hardviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MainModel model = new MainModel();
    private LiveData<List<Action>> actionsLiveData;
    private MutableLiveData<AppTheme> themeLiveData = new MutableLiveData<>();

    public MutableLiveData<AppTheme> getThemeLiveData() {
        return themeLiveData;
    }

    public LiveData<List<Action>> getActionsLiveData() {
        return actionsLiveData;
    }

    public void getActions() {
        actionsLiveData = model.getAllFromDB();
    }

    public void addNewAction(Action action) {
        model.addNewAction(action);
    }

    public void editAction(Action action) {
        model.editAction(action);
    }

    public void deleteAction(Action action) {
        model.deleteAction(action);
    }

    public void sendNewTheme(AppTheme theme) {
        themeLiveData.setValue(theme);
    }

}
