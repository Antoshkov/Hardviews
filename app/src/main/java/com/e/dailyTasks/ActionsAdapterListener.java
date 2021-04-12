package com.e.dailyTasks;

public interface ActionsAdapterListener {
    void createNewAction();
    void editChosenAction(Action chosenAction);
    void saveProgress(Action chosenAction);
}
