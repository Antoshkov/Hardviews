package com.e.hardviews;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.e.hardviews.DefaultAction.ALL;
import static com.e.hardviews.DefaultAction.BAD_HABITS;
import static com.e.hardviews.DefaultAction.FOOD;
import static com.e.hardviews.DefaultAction.HEALTH;
import static com.e.hardviews.DefaultAction.TIME;

public class CreateActionFragment extends BaseFragment implements DefaultActionAdapterListener, RadioGroup.OnCheckedChangeListener {

    public static final String ACTION_NAME = "actionName";
    public static final String ICON_ACTION = "iconAction";
    public static final String ICON_ACTION_REVERSE = "iconActionReverse";
    private CreateActionFragmentViewModel viewModel;
    private DefaultActionAdapter adapter;
    private RecyclerView recyclerView;
    private RadioGroup radioGroup;
    private RadioButton btnAll, btnHealth, btnFood, btnTime, btnBadHabits;
    private EditText etNewAction;
    private ImageView imgClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_create_action, container, false);
        initViews(view);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        adapter = new DefaultActionAdapter(this);
        recyclerView.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(this);
        etNewAction.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    Toast.makeText(getContext(), "go next", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        viewModel = ViewModelProviders.of(this).get(CreateActionFragmentViewModel.class);
        viewModel.getDefaultActions();
        viewModel.getDefaultActionLiveData().observe(getViewLifecycleOwner(), new Observer<List<DefaultAction>>() {
            @Override
            public void onChanged(List<DefaultAction> defaultActionList) {
                adapter.sendDefaultAction(defaultActionList);
            }
        });


        return view;
    }

    private void initViews(View view){
        recyclerView = view.findViewById(R.id.recycler);
        radioGroup = view.findViewById(R.id.radioGroup);
        etNewAction = view.findViewById(R.id.etCreateOwn);
        imgClose = view.findViewById(R.id.imgClose);
        btnAll = view.findViewById(R.id.radioBtn1);
        btnHealth = view.findViewById(R.id.radioBtn2);
        btnFood = view.findViewById(R.id.radioBtn3);
        btnTime = view.findViewById(R.id.radioBtn4);
        btnBadHabits = view.findViewById(R.id.radioBtn5);
    }

    @Override
    public void onClickChosenAction(DefaultAction chosenAction) {
        Bundle bundle = new Bundle();
        bundle.putString(ACTION_NAME, chosenAction.getNameAction());
        bundle.putInt(ICON_ACTION, chosenAction.getIconAction());
        bundle.putInt(ICON_ACTION_REVERSE, chosenAction.getIconActionReverse());
        navController.navigate(R.id.confirmActionFragment, bundle);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id){
            case R.id.radioBtn1:{
                adapter.sortByType(ALL);
                break;
            }
            case R.id.radioBtn2:{
                adapter.sortByType(HEALTH);
                break;
            }
            case R.id.radioBtn3:{
                adapter.sortByType(FOOD);
                break;
            }
            case R.id.radioBtn4:{
                adapter.sortByType(TIME);
                break;
            }
            case R.id.radioBtn5:{
                adapter.sortByType(BAD_HABITS);
                break;
            }
        }
    }
}