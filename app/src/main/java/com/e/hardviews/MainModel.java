package com.e.hardviews;

import java.util.ArrayList;
import java.util.List;

import static com.e.hardviews.DefaultAction.BAD_HABITS;
import static com.e.hardviews.DefaultAction.FOOD;
import static com.e.hardviews.DefaultAction.HEALTH;
import static com.e.hardviews.DefaultAction.TIME;

public class MainModel {

//    AppDatabase db = App.getInstance().getDatabase();
//    MyActionsDBDao actionsDBDao = db.actionsDBDao();
    private final String CREATE_ACTION = "Add a Task";
    private List<MyAction> actions = new ArrayList<>();
    private List<DefaultAction> defaultActions = new ArrayList<>();

    public MainModel (){
        defaultActions = createDefaultActions();
        actions.add(new MyAction("brush teeth", R.drawable.ic_tooth, R.drawable.ic_tooth_reverse, 2));
        actions.add(new MyAction("walk the dog", R.drawable.ic_dog_side, R.drawable.ic_dog_side_reverse, 3));
    }

    public List<MyAction> getActions() {
        return actions;
    }

    public List<DefaultAction> getDefaultActions() {
        return defaultActions;
    }

    public void addActionForCreate(){
        MyAction creator = new MyAction(CREATE_ACTION, R.drawable.ic_plus_thick, R.drawable.ic_plus_thick, 1);
        creator.setCreator(true);
        actions.add(creator);
    }

    public void deleteActionForCreate(){
        for (int i = 0; i < actions.size(); i++){
            MyAction action = actions.get(i);
            if (action.getNameAction().equals(CREATE_ACTION)){
                actions.remove(action);
            }
        }
    }
    public void addNewAction(MyAction newAction){
        actions.add(newAction);
    }

    private List<DefaultAction> createDefaultActions(){
        List<DefaultAction> defaultActionList = new ArrayList<>();
        defaultActionList.add(new DefaultAction("brush teeth", R.drawable.ic_tooth, R.drawable.ic_tooth_reverse, HEALTH));
        defaultActionList.add(new DefaultAction("walk the dog", R.drawable.ic_dog_side, R.drawable.ic_dog_side_reverse, TIME));
        defaultActionList.add(new DefaultAction("Running", R.drawable.ic_run_fast_reverse, R.drawable.ic_run_fast, HEALTH));
        defaultActionList.add(new DefaultAction("Take a vitamins", R.drawable.ic_pill_reverse, R.drawable.ic_pill, HEALTH));
        defaultActionList.add(new DefaultAction("Homework", R.drawable.ic_lead_pencil_reverse, R.drawable.ic_lead_pencil, TIME));
        defaultActionList.add(new DefaultAction("Read a book", R.drawable.ic_bookshelf_reverse, R.drawable.ic_bookshelf, TIME));
        defaultActionList.add(new DefaultAction("Cook a chiken", R.drawable.ic_food_turkey_reverse, R.drawable.ic_food_turkey, FOOD));
        defaultActionList.add(new DefaultAction("Take a food", R.drawable.ic_pasta_reverse, R.drawable.ic_pasta, FOOD));
        defaultActionList.add(new DefaultAction("No alcohol", R.drawable.ic_glass_cocktail_reverse, R.drawable.ic_glass_cocktail, BAD_HABITS));
        defaultActionList.add(new DefaultAction("No smoking", R.drawable.ic_smoking_off_reverse, R.drawable.ic_smoking_off, BAD_HABITS));
        return defaultActionList;
    }


}
