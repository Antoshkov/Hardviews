package com.e.hardviews;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.e.hardviews.CreateActionFragment.ACTION_NAME;
import static com.e.hardviews.CreateActionFragment.ICON_ACTION;
import static com.e.hardviews.CreateActionFragment.ICON_ACTION_REVERSE;
import static com.e.hardviews.CreateActionFragment.AMOUNT_OF_DAY;

public class MainFragment extends BaseFragment implements View.OnClickListener,
        ThemesAdapterListener, ActionsAdapterListener {

    public static final String CHOSEN_ACTION = "chosenAction";
    private MainViewModel viewModel;
    private ActionsAdapter mainItemAdapter;
    private ThemesAdapter backgroundAdapter;
    private RecyclerView recyclerMain, recyclerBackground;
    private ImageView imgMenu, imgSettings, imgStar, imgCloseSettings,
            imgTimeSettings, imgAnotherSettings, imgProperties;
    private LinearLayout linearLayoutSettings, linearLayoutBackgrounds;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        getActivity().setTheme(R.style.SportDesert);
        recyclerBackground.setAdapter(backgroundAdapter);
        recyclerMain.setAdapter(mainItemAdapter);
        bindOnClickListener();
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getActions();
        viewModel.getActionsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> actionList) {
                mainItemAdapter.getActions(actionList);
            }
        });
        return view;
    }

    private void bindOnClickListener(){
        imgStar.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        imgSettings.setOnClickListener(this);
        imgTimeSettings.setOnClickListener(this);
        imgAnotherSettings.setOnClickListener(this);
        imgCloseSettings.setOnClickListener(this);
        imgProperties.setOnClickListener(this);
    }

    private void initViews(View view){
        recyclerMain = view.findViewById(R.id.recycler);
        recyclerBackground = view.findViewById(R.id.recycleBackgrounds);
        mainItemAdapter = new ActionsAdapter(this);
        backgroundAdapter = new ThemesAdapter(this);
        imgMenu = view.findViewById(R.id.btnMenu);
        imgSettings = view.findViewById(R.id.btnOpenSettings);
        imgStar = view.findViewById(R.id.btnStar);
        imgAnotherSettings = view.findViewById(R.id.btnAnotherSettings);
        imgCloseSettings = view.findViewById(R.id.btnCloseSettings);
        imgProperties = view.findViewById(R.id.btnProperties);
        imgTimeSettings = view.findViewById(R.id.btnTime);
        linearLayoutBackgrounds = view.findViewById(R.id.linearLayoutBackgrounds);
        linearLayoutSettings = view.findViewById(R.id.linearLayoutSettings);
    }

    private void closeSettings(){
        viewModel.deleteActionCreator();
        mainItemAdapter.isSettingsOpen(false);
        linearLayoutSettings.setVisibility(View.GONE);
        linearLayoutBackgrounds.setVisibility(View.GONE);
    }

    private void openSettings(){
        mainItemAdapter.isSettingsOpen(true);
        viewModel.actionCreatorItem();
        linearLayoutSettings.setVisibility(View.VISIBLE);
        linearLayoutBackgrounds.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCloseSettings:{
                closeSettings();
                break;
            }
            case R.id.btnOpenSettings: {
               openSettings();
                break;
            }
        }
    }

    @Override
    public void changeTheme(int theme) {
        viewModel.sendNewTheme(theme);
    }


    @Override
    public void createNewAction() {
        navController.navigate(R.id.createActionFragment);
        closeSettings();
    }

    @Override
    public void editChosenAction(Action chosenAction) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CHOSEN_ACTION, chosenAction);
        navController.navigate(R.id.confirmEditActionFragment, bundle);
        closeSettings();
    }
}