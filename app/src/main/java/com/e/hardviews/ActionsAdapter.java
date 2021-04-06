package com.e.hardviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActionsAdapter extends RecyclerView.Adapter<ActionsAdapter.MyViewHolder> {

    private ActionsAdapterListener listener;
    private List<Action> actions;
    private boolean settingsCheck = false;

    ActionsAdapter(ActionsAdapterListener listener) {
        this.listener = listener;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Action chosenAction = actions.get(position);

        if (settingsCheck && !chosenAction.isCreator()) {
            holder.actionView.getBtnEdit().setVisibility(View.VISIBLE);
            holder.actionView.getBtnEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.editChosenAction(chosenAction);
                }
            });
        } else holder.actionView.getBtnEdit().setVisibility(View.GONE);
        holder.actionView.getTvNameAction().setText(chosenAction.getNameAction());
        holder.actionView.getIconAction().setBackgroundResource(chosenAction.getIconAction());
        holder.actionView.getIconActionReverse().setBackgroundResource(chosenAction.getIconActionReverse());
        holder.actionView.getProgressMain().setProgress(chosenAction.getProgress());
        holder.actionView.getPiecesView().setAmountTimes(chosenAction.getAmountPerDay());
        holder.actionView.getContainer().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (chosenAction.getCountPressedTimes() < chosenAction.getAmountPerDay()) {
                    int part = (100 / chosenAction.getAmountPerDay()) * chosenAction.getCountPressedTimes();
                    int percent = 100 / chosenAction.getAmountPerDay() + part;
                    int progress = chosenAction.getProgress();
                    holder.actionView.startAnimationProgress(progress, percent);
                    chosenAction.addPressedTimes();
                    chosenAction.setProgress(percent);

                }
                return false;
            }
        });

        holder.actionView.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenAction.isCreator()) {
                    listener.createNewAction();
                } else { }
            }
        });
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ActionView actionView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            actionView = itemView.findViewById(R.id.actionView);
        }
    }
}
