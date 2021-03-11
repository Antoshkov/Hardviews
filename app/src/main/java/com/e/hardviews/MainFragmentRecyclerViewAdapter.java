package com.e.hardviews;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentRecyclerViewAdapter extends RecyclerView.Adapter<MainFragmentRecyclerViewAdapter.MyViewHolder> {

    private MainFragmentRecyclerAdapterListener listener;
    private List<MyAction> myActions = new ArrayList<>();
    private int progress = 0;
    private int progressDay = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());

    MainFragmentRecyclerViewAdapter(MainFragmentRecyclerAdapterListener listener) {
        this.listener = listener;
    }

    public void getActions(List<MyAction> myActionList) {
        myActions = myActionList;
        notifyDataSetChanged();
    }

    private void progressRun(final MyViewHolder holder, final int percent) {
        progressDay++;
        holder.progressMain.setProgress(progressDay);
        if (progressDay < percent) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressRun(holder, percent);
                }
            }, 10);

        } else progressDay = percent;
    }

    private void progressRunOneTime(final MyViewHolder holder) {
        progress++;
        holder.progressOneTime.setVisibility(View.VISIBLE);
        holder.progressOneTime.setProgress(progress);
        if (progress < 100) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressRunOneTime(holder);
                }
            }, 10);
        } else {
            progressRunBackOneTime(holder);
        }
    }

    private void progressRunBackOneTime(final MyViewHolder holder) {
        progress--;
        holder.iconAction.setVisibility(View.GONE);
        holder.iconCheck.setVisibility(View.VISIBLE);
        holder.progressOneTime.setProgress(progress);
        if (progress > 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressRunBackOneTime(holder);
                }
            }, 10);
        } else {
            progress = 0;
            holder.iconCheck.setVisibility(View.GONE);
            holder.iconAction.setVisibility(View.VISIBLE);
            holder.progressOneTime.setVisibility(View.GONE);
        }
        if ((progressDay == 100 || progressDay == 99) && progress == 0) {
            holder.imgWellDone.setVisibility(View.VISIBLE);
            holder.iconCheckBlack.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.iconCheckBlack.setVisibility(View.GONE);
                    holder.iconActionBlack.setVisibility(View.VISIBLE);
                }
            }, 1000);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final MyAction chosenAction = myActions.get(position);
        holder.nameAction.setText(chosenAction.getNameAction());
        holder.iconAction.setBackgroundResource(chosenAction.getIconAction());
        holder.iconActionBlack.setBackgroundResource(chosenAction.getIconActionReverse());
        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (chosenAction.getCountPressedTimes() < chosenAction.getAmountPerDay()) {
                    int part = (100 / chosenAction.getAmountPerDay()) * chosenAction.getCountPressedTimes();
                    int percent = 100 / chosenAction.getAmountPerDay() + part;
                    progressRun(holder, percent);
                    progressRunOneTime(holder);
                    chosenAction.addPressedTimes();
                }
                return false;
            }
        });
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenAction.isCreator()) {
                    listener.createNewAction();
                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myActions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameAction;
        ConstraintLayout container;
        CircularSeekBar progressMain, progressOneTime;
        ImageView iconAction, iconCheck, imgWellDone, iconCheckBlack, iconActionBlack;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAction = itemView.findViewById(R.id.nameAction);
            container = itemView.findViewById(R.id.constContainer);
            progressMain = itemView.findViewById(R.id.progressMain);
            progressMain.setIsTouchEnabled(false);
            progressOneTime = itemView.findViewById(R.id.progressOneTime);
            iconAction = itemView.findViewById(R.id.iconAction);
            iconCheck = itemView.findViewById(R.id.checkedWhite);
            imgWellDone = itemView.findViewById(R.id.wellDone);
            iconActionBlack = itemView.findViewById(R.id.iconActionReverse);
            iconCheckBlack = itemView.findViewById(R.id.checkedBlack);


        }
    }
}
