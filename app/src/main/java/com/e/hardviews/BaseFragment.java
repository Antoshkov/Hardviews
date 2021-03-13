package com.e.hardviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BaseFragment extends Fragment {

    public NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText())
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
