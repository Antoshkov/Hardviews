package com.e.hardviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {

    private List<AppTheme> themes = new ArrayList<>();
    private ThemeAdapterListener listener;

    ThemeAdapter(ThemeAdapterListener listener){
        this.listener = listener;
        themes.add(new AppTheme(R.style.SportRed, R.color.sportRed));
        themes.add(new AppTheme(R.style.SportDesert, R.color.sportDesert));
        themes.add(new AppTheme(R.style.SportDarkGreen, R.color.sportDarkGreen));
        themes.add(new AppTheme(R.style.SportDarkBlue, R.color.sportDarkBlue));
        themes.add(new AppTheme(R.style.SportCalifornia, R.color.sportCalifornia));
        themes.add(new AppTheme(R.style.SportGray, R.color.sportGray));
        themes.add(new AppTheme(R.style.SportLightGreen, R.color.sportLightGreen));
        themes.add(new AppTheme(R.style.SportLightBlue, R.color.sportLightBlue));
        themes.add(new AppTheme(R.style.SportPurple, R.color.sportPurple));
        themes.add(new AppTheme(R.style.SportPink, R.color.sportPink));

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_background, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageView.setBackgroundResource(themes.get(position).getColor());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.changeTheme(themes.get(position).getThemeResource());
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgBackground);


        }
    }
}
