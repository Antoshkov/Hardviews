package com.e.hardviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainModel model = new MainModel();
    private MutableLiveData<List<MyAction>> actionsLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> themeLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getThemeLiveData() {
        return themeLiveData;
    }

    public MutableLiveData<List<MyAction>> getActionsLiveData() {
        return actionsLiveData;
    }

    public void getActions(){
        actionsLiveData.setValue(model.getActions());
    }

    public void actionPlus(){
        model.addActionForCreate();
        getActions();
    }

    public void deleteActionPlus(){
        model.deleteActionForCreate();
        getActions();
    }
    public void addNewAction(MyAction action){
        model.addNewAction(action);
        getActions();

    }

    public void sendNewTheme(int theme){
        themeLiveData.setValue(theme);
    }

}
