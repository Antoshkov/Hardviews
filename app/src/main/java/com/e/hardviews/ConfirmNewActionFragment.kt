package com.e.hardviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ConfirmNewActionFragment : BaseConfirmActionFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_confirm_action, container, false)
        initViews(view)
        super.onCreateView(inflater, container, savedInstanceState)
        btnSaveTask.setOnClickListener {
            val icon = requireArguments().getInt(CreateActionFragment.ICON_ACTION)
            val iconRev = requireArguments().getInt(CreateActionFragment.ICON_ACTION_REVERSE)
            viewModel.addNewAction(Action(actionName.text.toString(), icon, iconRev, countTimes))
            navController.navigate(R.id.mainFragment)
        }
        return view
    }

}