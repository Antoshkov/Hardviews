package com.e.dailyTasks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.e.dailyTasks.MainActivity.THEME_COLOR;
import static com.e.dailyTasks.MainFragment.CREATOR_ACTION;

public class ActionsAdapter extends RecyclerView.Adapter<ActionsAdapter.MyViewHolder>
        implements ActionView.SaveProgressListener {

    private final ActionsAdapterListener listener;
    private List<Action> actions = new ArrayList<>();
    private boolean settingsCheck = false;
    private final int piecesColor;
    private Action itemAction;

    ActionsAdapter(Activity activity, ActionsAdapterListener listener) {
        this.listener = listener;
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        piecesColor = preferences.getInt(THEME_COLOR, activity.getColor(R.color.sportDesert));
    }

    public void addCreatorAction() {
        Action creatorAction = new Action(CREATOR_ACTION,
                R.drawable.ic_plus_thick,
                R.drawable.ic_plus_thick,
                1, 1);
        creatorAction.setCreator(true);
        boolean hasCreator = false;
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).isCreator()) hasCreator = true;
        }
        if (!hasCreator) actions.add(creatorAction);
        notifyDataSetChanged();
    }

    public void deleteCreatorAction() {
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            if (action.getNameAction().equals(CREATOR_ACTION)) actions.remove(action);
        }
        if (actions.isEmpty()) addCreatorAction();
        notifyDataSetChanged();
    }

    public void getActions(List<Action> actionList) {
        actions = actionList;
        notifyDataSetChanged();
    }

    public void isSettingsOpen(boolean isSettingsOpen) {
        settingsCheck = isSettingsOpen;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Action chosenAction = actions.get(position);
        final int currentProgress =
                (100 / chosenAction.getAmountPerDay()) * chosenAction.getCountPressedTimes();
        if (settingsCheck && !chosenAction.isCreator()) {
            holder.actionView.getBtnEdit().setVisibility(View.VISIBLE);
            holder.actionView.getBtnEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.editChosenAction(chosenAction);
                }
            });
        } else holder.actionView.getBtnEdit().setVisibility(View.GONE);
        holder.actionView.resetProgress();
        holder.actionView.attachListener(this);
        holder.actionView.getTvNameAction().setText(chosenAction.getNameAction());
        holder.actionView.getIconAction().setBackgroundResource(chosenAction.getIconAction());
        holder.actionView.getIconActionReverse()
                .setBackgroundResource(chosenAction.getIconActionReverse());
        holder.actionView.getProgressMain().setProgress((currentProgress));
        holder.actionView.getPiecesView().setPaintColor(piecesColor);
        holder.actionView.getPiecesView().setAmountTimes(chosenAction.getAmountPerDay());
        holder.actionView.getContainer().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if ((chosenAction.getCountPressedTimes() < chosenAction.getAmountPerDay())
                        && !chosenAction.isCreator()) {
                    int newProgress = 100 / chosenAction.getAmountPerDay() + currentProgress;
                    int progress = chosenAction.getProgress();
                    holder.actionView.startAnimationProgress(progress, newProgress);
                    chosenAction.addPressedTimes();
                    chosenAction.setProgress(newProgress);
                    itemAction = chosenAction;
                }
                return false;
            }
        });
        holder.actionView.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenAction.isCreator()) listener.createNewAction();
                else { }
            }
        });
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    @Override
    public void animationEnded() {
        listener.saveProgress(itemAction);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ActionView actionView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            actionView = itemView.findViewById(R.id.actionView);
        }
    }
}
