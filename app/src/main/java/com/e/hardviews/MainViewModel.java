package com.e.hardviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MainModel model = new MainModel();
    private List<MyAction> actions = new ArrayList<>();
    private MutableLiveData<List<MyAction>> actionsLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> themeLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getThemeLiveData() {
        return themeLiveData;
    }

    public MutableLiveData<List<MyAction>> getActionsLiveData() {
        return actionsLiveData;
    }

    public void getActions(){
        actions = model.getActions();
        if (!actions.isEmpty()){
            actionsLiveData.setValue(actions);
        } else {
            model.addActionForCreate();
            getActions();
        }
    }

    public void actionCreatorItem(){
        model.addActionForCreate();
        getActions();
    }

    public void deleteActionCreator(){
        model.deleteActionForCreate();
        getActions();
    }

    public void addNewAction(MyAction action){
        model.addNewAction(action);
        deleteActionCreator();
        getActions();
    }

    public void editAction(MyAction action){
        model.editAction(action);
        getActions();
    }

    public void sendNewTheme(int theme){
        themeLiveData.setValue(theme);
    }

}
