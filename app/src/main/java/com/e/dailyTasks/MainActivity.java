package com.e.dailyTasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String THEME_COLOR = "themeColor";
    private final String THEME = "theme";
    private Intent intent;
    private MainViewModel viewModel;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getPreferences(MODE_PRIVATE);
        setTheme(preferences.getInt(THEME, R.style.SportDesert));
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        intent = new Intent(this, MainActivity.class);
        viewModel.getThemeLiveData().observe(this, new Observer<AppTheme>() {
            @Override
            public void onChanged(AppTheme newTheme) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(THEME, newTheme.getThemeResource());
                editor.putInt(THEME_COLOR, newTheme.getColorForCanvas());
                editor.apply();
                finish();
                intent.putExtra("extra", newTheme.getThemeResource());
                startActivity(intent);
            }
        });
    }
}