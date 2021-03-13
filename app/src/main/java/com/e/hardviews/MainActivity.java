package com.e.hardviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Intent i;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("extra"))
            setTheme(getIntent().getIntExtra("extra", R.style.AppTheme));
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        i = new Intent(this, MainActivity.class);
        viewModel.getThemeLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer newTheme) {
                finish();
                i.putExtra("extra", newTheme);
                startActivity(i);
            }
        });
    }
}