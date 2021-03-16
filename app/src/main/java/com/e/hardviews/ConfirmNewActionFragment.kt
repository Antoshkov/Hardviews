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
            val icon = action!!.iconAction
            val iconRev = action!!.iconActionReverse
            val type = action!!.actionType
            viewModel.addNewAction(Action(actionName.text.toString(), icon, iconRev, countTimes, type))
            navController.navigate(R.id.mainFragment)
        }
        return view
    }

}