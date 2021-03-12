package com.e.hardviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.e.hardviews.DefaultAction.ALL;

public class DefaultActionAdapter extends RecyclerView.Adapter<DefaultActionAdapter.MyViewHolder> {

    private List<DefaultAction> defaultActions = new ArrayList<>();
    private List<DefaultAction> reserveList = new ArrayList<>();

    private DefaultActionAdapterListener listener;

    DefaultActionAdapter(DefaultActionAdapterListener listener) {
        this.listener = listener;
    }

    void sendDefaultAction(List<DefaultAction> defaultActionList) {
        defaultActions = defaultActionList;
        reserveList = defaultActionList;
        notifyDataSetChanged();
    }

    void sortByType(int type) {
        sendDefaultAction(reserveList);
        if (type != ALL) {
            List<DefaultAction> actions = new ArrayList<>();
            for (int i = 0; i < defaultActions.size(); i++) {
                DefaultAction action = defaultActions.get(i);
                if (action.getActionType() == type) {
                    actions.add(action);
                }
            }
            defaultActions = actions;
        } else {
            defaultActions = reserveList;
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_action, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageAction.setBackgroundResource(defaultActions.get(position).getIconAction());
        holder.nameAction.setText(defaultActions.get(position).getNameAction());
        holder.imgGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickChosenAction(defaultActions.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return defaultActions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAction, imgGoNext;
        TextView nameAction;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAction = itemView.findViewById(R.id.imgCreateAction);
            imgGoNext = itemView.findViewById(R.id.imgGoNext);
            nameAction = itemView.findViewById(R.id.tvNameActionCreate);


        }
    }
}
