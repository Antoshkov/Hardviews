package com.e.hardviews;

import java.util.ArrayList;
import java.util.List;

import static com.e.hardviews.Action.BAD_HABITS;
import static com.e.hardviews.Action.FOOD;
import static com.e.hardviews.Action.HEALTH;
import static com.e.hardviews.Action.TIME;

public class MainModel {

    AppDatabase db = App.getInstance().getDatabase();
    MyActionsDBDao actionsDBDao = db.actionsDBDao();

    private final String CREATE_ACTION = "Add a Task";
    private List<Action> actions;
    private final List<Action> defaultActions;

    public MainModel (){
        actions = actionsDBDao.getAll();
        defaultActions = createDefaultActions();
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Action> getDefaultActions() {
        return defaultActions;
    }

    public void addActionForCreate(){
        boolean checkCreator = false;
        for (int i = 0; i < actions.size(); i++){
            Action action = actions.get(i);
            if (action.isCreator()) checkCreator = true;
        }
        if (!checkCreator){
            Action creator = new Action(CREATE_ACTION, R.drawable.ic_plus_thick, R.drawable.ic_plus_thick, 1, 1);
            creator.setCreator(true);
            actions.add(creator);
        }
    }

    public void deleteActionForCreate(){
        for (int i = 0; i < actions.size(); i++){
            Action action = actions.get(i);
            if (action.getNameAction().equals(CREATE_ACTION)){
                actions.remove(action);
            }
        }
    }

    public void addNewAction(Action newAction){
        actionsDBDao.insert(newAction);
        actions = actionsDBDao.getAll();
    }

    public void editAction(Action chosenAction){
        actionsDBDao.insert(chosenAction);
        actions = actionsDBDao.getAll();
    }

    public void deleteAction(Action action){
        actionsDBDao.delete(action);
        actions = actionsDBDao.getAll();
    }

    private List<Action> createDefaultActions(){
        List<Action> defaultActionList = new ArrayList<>();
        defaultActionList.add(new Action("brush teeth", R.drawable.ic_tooth, R.drawable.ic_tooth_reverse, 1, HEALTH));
        defaultActionList.add(new Action("walk the dog", R.drawable.ic_dog_side, R.drawable.ic_dog_side_reverse, 1,TIME));
        defaultActionList.add(new Action("Running", R.drawable.ic_run_fast_reverse, R.drawable.ic_run_fast, 1,HEALTH));
        defaultActionList.add(new Action("Take a vitamins", R.drawable.ic_pill_reverse, R.drawable.ic_pill, 1,HEALTH));
        defaultActionList.add(new Action("Homework", R.drawable.ic_lead_pencil_reverse, R.drawable.ic_lead_pencil, 1,TIME));
        defaultActionList.add(new Action("Read a book", R.drawable.ic_bookshelf_reverse, R.drawable.ic_bookshelf, 1,TIME));
        defaultActionList.add(new Action("Cook a chiken", R.drawable.ic_food_turkey_reverse, R.drawable.ic_food_turkey, 1,FOOD));
        defaultActionList.add(new Action("Take a food", R.drawable.ic_pasta_reverse, R.drawable.ic_pasta, 1,FOOD));
        defaultActionList.add(new Action("No alcohol", R.drawable.ic_glass_cocktail_reverse, R.drawable.ic_glass_cocktail, 1,BAD_HABITS));
        defaultActionList.add(new Action("No smoking", R.drawable.ic_smoking_off_reverse, R.drawable.ic_smoking_off, 1,BAD_HABITS));
        return defaultActionList;
    }


}
